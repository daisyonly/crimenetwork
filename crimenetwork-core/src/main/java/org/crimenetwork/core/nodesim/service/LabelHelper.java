package org.crimenetwork.core.nodesim.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.crimenetwork.core.nodesim.SimRankFeatureGenerator;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
import org.crimenetwork.neo4j.repository.CrimeCaseRepository;
import org.crimenetwork.neo4j.repository.SuspectInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("labelHelper")
public class LabelHelper {
	
	private SimRankFeatureGenerator simRankFeatureGenerator=new SimRankFeatureGenerator();
	
	@Autowired
	private SuspectInfoRepository suspectInfoRepository;
	
	@Autowired
	private CrimeCaseRepository crimeCaseRepository;
	
	@Autowired
	private CounterfeitMoneyRepository counterfeitMoneyRepository;
	
	public List<FeatureEntity> generateLabeldata(String query,List<Long> candidatesId){
 		char flag=query.charAt(0);
 		Long queryId = Long.parseLong(query.substring(1));
 		List<NodeFeature> res=new ArrayList<NodeFeature>();
 		if(flag=='J'){
 			CounterfeitMoney queryObject= counterfeitMoneyRepository.findByFmid(queryId);
 		    List<CounterfeitMoney> candidates=new ArrayList<CounterfeitMoney>();
 		    for(Long id:candidatesId){
 		    	candidates.add(counterfeitMoneyRepository.findByFmid(id));
 		    }
 		   
 		    for(CounterfeitMoney cm:candidates){
 		    	List<Double> features=simRankFeatureGenerator.generate(queryObject, cm);
 		    	String curKey= flag+""+cm.getFmid();
 		    	res.add(new NodeFeature(curKey,features));	
 		    }
 		}else if(flag=='C'){
 			CrimeCase queryObject= crimeCaseRepository.findByCId(queryId);
 		    List<CrimeCase> candidates=new ArrayList<CrimeCase>();
 		    for(Long id:candidatesId){
 		    	candidates.add(crimeCaseRepository.findByCId(id));
 		    }
 		   
 		    for(CrimeCase cm:candidates){
 		    	List<Double> features=simRankFeatureGenerator.generate(queryObject, cm);
 		    	String curKey= flag+""+cm.getcId();
 		    	res.add(new NodeFeature(curKey,features));
 		    }
 		    
 		}else{
 			SuspectInfo queryObject= suspectInfoRepository.findBySId(queryId);
 		    List<SuspectInfo> candidates=new ArrayList<SuspectInfo>();
 		    for(Long id:candidatesId){
 		    	SuspectInfo caInfo=suspectInfoRepository.findBySId(id);
 		    	if(caInfo==null) System.out.println(id);
 		    	candidates.add(caInfo);
 		    }
 		    for(SuspectInfo cm:candidates){
 		    	List<Double> features=simRankFeatureGenerator.generate(queryObject, cm);		    	
 		    	String curKey= flag+""+cm.getsId();
 		    	res.add(new NodeFeature(curKey,features));
 		    }
 		}
 		
 		Map<String, Integer> labelResult =labelData(flag,res);
 		List<FeatureEntity> realRes=new ArrayList<FeatureEntity>();
 		for(NodeFeature node:res){
 			realRes.add(new FeatureEntity(node.id,labelResult.get(node.id),node.feature));
 		}
 		return realRes;
 	}
	
	private Map<String, Integer> labelData(char flag,List<NodeFeature> features){	
		List<SortNode> resList=new ArrayList<SortNode>();
		for(NodeFeature feature: features){
			double sum=getSum(flag,feature.feature);
			resList.add(new SortNode(feature.id, sum));
		}
		Collections.sort(resList, new Comparator<SortNode>(){
				@Override
				public int compare(SortNode o1, SortNode o2) {				
					return (int) (o2.score-o1.score);
				}	
			});
		
		Map<String, Integer> rankMap=new HashMap<String, Integer>();
		int interval=resList.size()/4;
		int curRank=3;
		rankMap.put(resList.get(0).id, curRank);
		int curCount=1;
		for(int i=1;i<resList.size();i++){
			if(Math.abs(resList.get(i).score-resList.get(i-1).score)<1){
				
			}else if(Math.abs(resList.get(i).score-resList.get(i-1).score)>50){
				if(curRank>0) curRank--;
			}else if(curCount==interval){
				curRank--;
				curCount=1;
			}else{
				curCount++;
			}
			rankMap.put(resList.get(i).id, curRank);
		}
		return rankMap;
	}
	
	private double getSum(char flag, List<Double> feature){
		List<String> header=null;
		if(flag=='S'){
			header=simRankFeatureGenerator.suspectFeatureHeader;
		}else if(flag=='J'){
			header=simRankFeatureGenerator.counterfeitMoneyFeatureHeader;
		}else{
			header=simRankFeatureGenerator.caseFeatureHeader;
		}
		double sum=0;
		for(int i=0;i<header.size();i++){
			if(header.get(i).indexOf('_')>0){
				String[] tmps=header.get(i).split("_");
				int weight=10-tmps[1].length();
				sum+=feature.get(i)*weight;
			}else{
				sum+=feature.get(i);
			}
		}
		return sum;
	}
	
	class NodeFeature{
		String id;
		List<Double> feature;
		NodeFeature(String id,List<Double> feature){
			this.id=id;
			this.feature=feature;
		}
	}
	
	public class FeatureEntity{
		public String id;
		public int label;
		public List<Double> feature;
		FeatureEntity(String id,int label,List<Double> feature){
			this.id=id;
			this.label=label;
			this.feature=feature;
		}
	}
    
	class SortNode{
		String id;
		double score;
		SortNode(String id,double score){
			this.id=id;
			this.score=score;
		}
	}

}
