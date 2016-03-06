package org.crimenetwork.core.api;


import java.util.LinkedList;
import java.util.Queue;

import org.crimenetwork.core.entity.NetworkEdge;
import org.crimenetwork.core.entity.NetworkModel;
import org.crimenetwork.core.service.EntityToMapConverter;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
import org.crimenetwork.neo4j.repository.CrimeCaseRepository;
import org.crimenetwork.neo4j.repository.SuspectInfoRepository;
import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.internalDocBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class SearchController{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private SuspectInfoRepository suspectInfoRepository;
	
	@Autowired
	private CrimeCaseRepository crimeCaseRepository;
	
	@Autowired
	private CounterfeitMoneyRepository counterfeitMoneyRepository;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public NetworkModel loadScriptContent() {
		logger.debug("here!");
		String id="R4401115800002008091463";
		NetworkModel res=searchByObjectId(id, 4);
		System.out.println("hehe!!");
		return res;
	}
	
	private NetworkModel searchByObjectId(String id,int pathLength){
		NetworkModel  res=new NetworkModel();
		Queue<String> queue= new LinkedList<String>();
		if(id.charAt(0)=='R'){
			SuspectInfo start=suspectInfoRepository.findBySuspectId(id);	
			queue.add("S"+start.getId());
		}else if(id.charAt(0)=='C'){
			CounterfeitMoney start=counterfeitMoneyRepository.findByPiaoyangNumber(id);	
			queue.add("J"+start.getId());
		}else{
			CrimeCase start=crimeCaseRepository.findByCaseId(id);	
			queue.add("C"+start.getId());
		}
		
		while(!queue.isEmpty()&& pathLength--!=0){
			int levelNodeCount=queue.size();
			while(levelNodeCount--!=0){
				String peekString= queue.poll();
				if(peekString.charAt(0)=='S'){
					Long peekId=Long.parseLong(peekString.substring(1));
					SuspectInfo peekNode=suspectInfoRepository.findBySId(peekId);
					res.nodes.put(peekString, EntityToMapConverter.convert(peekNode));
					for(CrimeCase crimeCase:peekNode.getCases()){
						String key="C"+crimeCase.getcId();
						NetworkEdge edge=new NetworkEdge(peekString,key);
						NetworkEdge edge2=new NetworkEdge(key,peekString);
						res.edges.add(edge);
						res.edges.add(edge2);
						queue.add(key);
					}
					for(SuspectInfo suspectInfo:peekNode.getIdenticalSuspects()){
						String key="S"+suspectInfo.getsId();
						NetworkEdge edge=new NetworkEdge(peekString,key);
						NetworkEdge edge2=new NetworkEdge(key,peekString);
						res.edges.add(edge);
						res.edges.add(edge2);
						queue.add(key);
					}
				}else if(peekString.charAt(0)=='C'){
					Long peekId=Long.parseLong(peekString.substring(1));
					CrimeCase peekNode=crimeCaseRepository.findByCId(peekId);
					res.nodes.put(peekString, EntityToMapConverter.convert(peekNode));
					for(SuspectInfo suspectInfo:peekNode.getSuspects()){
						String key="S"+suspectInfo.getsId();
						NetworkEdge edge=new NetworkEdge(peekString,key);
						NetworkEdge edge2=new NetworkEdge(key,peekString);
						res.edges.add(edge);
						res.edges.add(edge2);
						queue.add(key);
					}
					for(CounterfeitMoney counterfeitMoney:peekNode.getCounterfeitMoneys()){
						String key="J"+counterfeitMoney.getFmid();
						NetworkEdge edge=new NetworkEdge(peekString,key);
						NetworkEdge edge2=new NetworkEdge(key,peekString);
						res.edges.add(edge);
						res.edges.add(edge2);
						queue.add(key);
					}
				}else{
					Long peekId=Long.parseLong(peekString.substring(1));
					CounterfeitMoney peekNode=counterfeitMoneyRepository.findByFmid(peekId);
					res.nodes.put(peekString, EntityToMapConverter.convert(peekNode));
					for(CrimeCase crimeCase:peekNode.getCaseInfos()){
						String key="C"+crimeCase.getcId();
						NetworkEdge edge=new NetworkEdge(peekString,key);
						NetworkEdge edge2=new NetworkEdge(key,peekString);
						res.edges.add(edge);
						res.edges.add(edge2);
						queue.add(key);
					}
					for(CounterfeitMoney counterfeitMoney:peekNode.getSimilarCM()){
						String key="J"+counterfeitMoney.getFmid();
						NetworkEdge edge=new NetworkEdge(peekString,key);
						NetworkEdge edge2=new NetworkEdge(key,peekString);
						res.edges.add(edge);
						res.edges.add(edge2);
						queue.add(key);
					}
				}
			}
			
		}
		return res;
	}
	

}
