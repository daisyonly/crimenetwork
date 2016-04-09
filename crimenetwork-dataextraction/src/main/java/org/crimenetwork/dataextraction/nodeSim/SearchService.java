package org.crimenetwork.dataextraction.nodeSim;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.entity.relationship.CurrencySim;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
import org.crimenetwork.neo4j.repository.CrimeCaseRepository;
import org.crimenetwork.neo4j.repository.SuspectInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("searchService")
public class SearchService {
	
	@Autowired
	private SuspectInfoRepository suspectInfoRepository;
	
	@Autowired
	private CrimeCaseRepository crimeCaseRepository;
	
	@Autowired
	private CounterfeitMoneyRepository counterfeitMoneyRepository;
	
	private class SearchNode{
		char flag;
		public SearchNode(char flag){
			this.flag=flag;
			sameAslastNode=false;
		}
		SuspectInfo suspectInfo;
		CrimeCase crimeCase;
		CounterfeitMoney counterfeitMoney;
		
		boolean sameAslastNode;
	}
	
	public NetworkModel searchBySuspectInfo(SuspectInfo suspectInfo,int pathLength){
		Queue<SearchNode> queue= new LinkedList<SearchNode>();
		HashSet<String> visitedSet =new HashSet<String>();
			SuspectInfo start=suspectInfo;	
			SearchNode node=new SearchNode('S');
			node.suspectInfo=start;
			queue.add(node);
			visitedSet.add("S"+start.getsId());	
		return search(queue,visitedSet,pathLength);	
	}
	
	private NetworkModel search(Queue<SearchNode> queue,HashSet<String> visitedSet,int pathLength){
		NetworkModel  res = new NetworkModel();
		while(!queue.isEmpty()&& pathLength--!=0){
			int levelNodeCount=queue.size();
			while(levelNodeCount--!=0){
				SearchNode peek= queue.poll();
				if(peek.flag =='S'){
					SuspectInfo peekNode=peek.suspectInfo;
					String peekKey="S"+peekNode.getsId();
					if(pathLength==res.suspectLevel||res.nearestSuspectNode.size()==0){
						res.nearestSuspectNode.add(peekKey);
						res.suspectLevel=pathLength;
					}
					res.nodes.put(peekKey, EntityToMapConverter.convert(peekNode));
					for(CrimeCase crimeCase:peekNode.getCases()){
						String key="C"+crimeCase.getcId();
						NetworkEdge edge=new NetworkEdge(peekKey,key);
						NetworkEdge edge2=new NetworkEdge(key,peekKey);
						res.edges.add(edge);
						res.edges.add(edge2);
						SearchNode newNode=new SearchNode('C');
						newNode.crimeCase=crimeCase;
						if(visitedSet.contains(key)) continue;
						visitedSet.add(key);
						queue.add(newNode);
					}
					if(peek.sameAslastNode==false){
						for(SuspectInfo suspectInfo:peekNode.getIdenticalSuspects()){
							String key="S"+suspectInfo.getsId();
							NetworkEdge edge=new NetworkEdge(peekKey,key);
							NetworkEdge edge2=new NetworkEdge(key,peekKey);
							res.edges.add(edge);
							res.edges.add(edge2);
							SearchNode newNode=new SearchNode('S');
							newNode.suspectInfo=suspectInfo;
							newNode.sameAslastNode=true;
							if(visitedSet.contains(key)) continue;
							visitedSet.add(key);
							queue.add(newNode);
						}
					}
				}else if(peek.flag=='C'){				
					CrimeCase peekNode=peek.crimeCase;
					String peekKey="C"+peekNode.getcId();
					
					if(pathLength==res.caseLevel||res.nearestCaseNode.size()==0){
						res.nearestCaseNode.add(peekKey);
						res.caseLevel=pathLength;
					}
					
					res.nodes.put(peekKey, EntityToMapConverter.convert(peekNode));
					for(SuspectInfo suspectInfo:peekNode.getSuspects()){
						String key="S"+suspectInfo.getsId();
						NetworkEdge edge=new NetworkEdge(peekKey,key);
						NetworkEdge edge2=new NetworkEdge(key,peekKey);
						res.edges.add(edge);
						res.edges.add(edge2);
						SearchNode newNode=new SearchNode('S');
						newNode.suspectInfo=suspectInfo;
						if(visitedSet.contains(key)) continue;
						visitedSet.add(key);
						queue.add(newNode);
					}
					for(CounterfeitMoney counterfeitMoney:peekNode.getCounterfeitMoneys()){
						String key="J"+counterfeitMoney.getFmid();
						NetworkEdge edge=new NetworkEdge(peekKey,key);
						NetworkEdge edge2=new NetworkEdge(key,peekKey);
						res.edges.add(edge);
						res.edges.add(edge2);
						SearchNode newNode=new SearchNode('J');
						newNode.counterfeitMoney=counterfeitMoney;
						if(visitedSet.contains(key)) continue;
						visitedSet.add(key);
						queue.add(newNode);
					}
				}else{					
					CounterfeitMoney peekNode=peek.counterfeitMoney;
					String peekKey="J"+peekNode.getFmid();
					
					if(pathLength==res.cMLevel||res.nearestCMNode.size()==0){
						res.nearestCMNode.add(peekKey);
						res.cMLevel=pathLength;
					}
					
					res.nodes.put(peekKey, EntityToMapConverter.convert(peekNode));
					for(CrimeCase crimeCase:peekNode.getCaseInfos()){
						String key="C"+crimeCase.getcId();
						NetworkEdge edge=new NetworkEdge(peekKey,key);
						NetworkEdge edge2=new NetworkEdge(key,peekKey);
						res.edges.add(edge);
						res.edges.add(edge2);
						SearchNode newNode=new SearchNode('C');
						newNode.crimeCase=crimeCase;
						if(visitedSet.contains(key)) continue;
						visitedSet.add(key);
						queue.add(newNode);
					}
					if(peek.sameAslastNode==false){
						for(CurrencySim currencySim:peekNode.getSimilarCM()){
							String key="J"+currencySim.getTo().getFmid();
							NetworkEdge edge=new NetworkEdge(peekKey,key);
							NetworkEdge edge2=new NetworkEdge(key,peekKey);
							res.edges.add(edge);
							res.edges.add(edge2);
							SearchNode newNode=new SearchNode('J');
							newNode.counterfeitMoney=currencySim.getTo();
							newNode.sameAslastNode=true;
							if(visitedSet.contains(key)) continue;
							visitedSet.add(key);
							queue.add(newNode);
						}
					}
				}
			}	
		}
		return res;
	}
	
	/*
	private NetworkModel searchByObjectId(String id,int pathLength){
		NetworkModel  res=new NetworkModel();
		Queue<String> queue= new LinkedList<String>();
		if(id.charAt(0)=='R'){
			SuspectInfo start=suspectInfoRepository.findBySuspectId(id);	
			queue.add("S"+start.getsId());
		}else if(id.charAt(0)=='C'){
			CounterfeitMoney start=counterfeitMoneyRepository.findByPiaoyangNumber(id);	
			queue.add("J"+start.getFmid());
		}else{
			CrimeCase start=crimeCaseRepository.findByCaseId(id);	
			queue.add("C"+start.getcId());
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
					for(CurrencySim currencySim:peekNode.getSimilarCM()){
						String key="J"+currencySim.getTo().getFmid();
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
	*/

}
