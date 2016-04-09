package org.crimenetwork.core.nodesim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.crimenetwork.core.metapath.MetaGraph;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;

public class SimRankFeatureGenerator {
	
	public List<String> caseFeatureHeader=null;
	
	public List<String> suspectFeatureHeader=null;
	
	public List<String> counterfeitMoneyFeatureHeader=null;
	private MetaPathFeatureGenerator metaPathFeatureGenerator=null;
	
	private List<String> caseMetaPaths=null;
	private List<String> suspectMetaPaths=null;
	private List<String> counterfeitMoneyMetaPaths=null;
	
	public SimRankFeatureGenerator(){
		MetaGraph mg=MetaGraph.getAnInstance();
		
		caseFeatureHeader=new ArrayList<String>();
		caseMetaPaths=mg.getCaseMetaPaths();
		for(String path:mg.getCaseMetaPaths()){
			caseFeatureHeader.add("pathCout_"+path);
			caseFeatureHeader.add("normalizedPathCount_"+path);
			caseFeatureHeader.add("randomWalk_"+path);
			caseFeatureHeader.add("symmetricRandomWalk_"+path);
		}
		caseFeatureHeader.addAll(AttrSimCalculator.getCaseAttrNameList());
		
		suspectFeatureHeader=new ArrayList<String>();
		suspectMetaPaths=mg.getSuspectMetaPaths();
		for(String path:mg.getSuspectMetaPaths()){
			suspectFeatureHeader.add("pathCout_"+path);
			suspectFeatureHeader.add("normalizedPathCount_"+path);
			suspectFeatureHeader.add("randomWalk_"+path);
			suspectFeatureHeader.add("symmetricRandomWalk_"+path);
		}
		suspectFeatureHeader.addAll(AttrSimCalculator.getSuspectAttrNameList());
		
		counterfeitMoneyMetaPaths=mg.getCounterfeitMoneyMetaPaths();
		counterfeitMoneyFeatureHeader=new ArrayList<String>();
		for(String path:mg.getCounterfeitMoneyMetaPaths()){
			counterfeitMoneyFeatureHeader.add("pathCout_"+path);
			counterfeitMoneyFeatureHeader.add("normalizedPathCount_"+path);
			counterfeitMoneyFeatureHeader.add("randomWalk_"+path);
			counterfeitMoneyFeatureHeader.add("symmetricRandomWalk_"+path);
		}
		counterfeitMoneyFeatureHeader.addAll(AttrSimCalculator.getCounterfeitMoneyAttrNameList());
	
		metaPathFeatureGenerator=new MetaPathFeatureGenerator();
	}
	
	public List<Double> generate(CrimeCase query,CrimeCase object){	
		HashMap<String, MetaPathFeature> metaPathFeatures= metaPathFeatureGenerator.generate(query);
		String objectKey="C"+object.getcId();
		HashMap<String, Double> attriFeatures=AttrSimCalculator.computeSimilar(query, object);
		List<Double> features=generateMetaPathfeature(caseMetaPaths,metaPathFeatures,objectKey);
		for(String key:AttrSimCalculator.getCaseAttrNameList()){
			features.add(attriFeatures.get(key));
		}	
		return features;
	}
	
	public List<Double> generate(CounterfeitMoney query,CounterfeitMoney object){
		HashMap<String, MetaPathFeature> metaPathFeatures= metaPathFeatureGenerator.generate(query);
		String objectKey="J"+object.getFmid();
		HashMap<String, Double> attriFeatures=AttrSimCalculator.computeSimilar(query, object);
		List<Double> features=generateMetaPathfeature(counterfeitMoneyMetaPaths,metaPathFeatures,objectKey);
		for(String key:AttrSimCalculator.getCounterfeitMoneyAttrNameList()){
			features.add(attriFeatures.get(key));
		}	
		return features;
		
	}
	
	public List<Double> generate(SuspectInfo query,SuspectInfo object){
		HashMap<String, MetaPathFeature> metaPathFeatures= metaPathFeatureGenerator.generate(query);
		String objectKey="S"+object.getsId();
		HashMap<String, Double> attriFeatures=AttrSimCalculator.computeSimilar(query, object);
		List<Double> features=generateMetaPathfeature(suspectMetaPaths,metaPathFeatures,objectKey);
		for(String key:AttrSimCalculator.getSuspectAttrNameList()){
			features.add(attriFeatures.get(key));
		}	
		return features;
		
	}
	
	public Map<String, Double> getFeatureMap(char flag,List<Double> features){
		List<String> header=null;
		if(flag=='J') header=counterfeitMoneyFeatureHeader;
		else if(flag=='C') header=caseFeatureHeader;
		else header=suspectFeatureHeader;
		Map<String, Double> tmp=new HashMap<String, Double>();
		for(int i=0;i<header.size();i++){
			tmp.put(header.get(i), features.get(i));
		}
		return tmp;
	}
	
	public String outputFeature(char flag,List<Double> features){
		List<String> header=null;
		if(flag=='J') header=counterfeitMoneyFeatureHeader;
		else if(flag=='C') header=caseFeatureHeader;
		else header=suspectFeatureHeader;
		StringBuilder tmpBuilder=new StringBuilder();
		for(int i=0;i<header.size();i++){
			tmpBuilder.append(header.get(i)+":"+features.get(i)+"\t");
		}
		return tmpBuilder.toString();
	}
	
	private List<Double> generateMetaPathfeature(List<String> metaPaths,HashMap<String, MetaPathFeature> metaPathFeatures,String objectKey){
		List<Double> features= new ArrayList<Double>();
		if(metaPathFeatures.containsKey(objectKey)){
			MetaPathFeature metaPathFeature=metaPathFeatures.get(objectKey);
			for(int i=0;i<metaPaths.size();i++){
				String pathString=metaPaths.get(i);
				if(metaPathFeature.pathCout.containsKey(pathString)){
					features.add(metaPathFeature.pathCout.get(pathString));
				}else{
					features.add((double) 0);
				}
				
				if(metaPathFeature.normalizedPathCount.containsKey(pathString)){
					features.add(metaPathFeature.normalizedPathCount.get(pathString));
				}else{
					features.add((double) 0);
				}
				
				if(metaPathFeature.randomWalk.containsKey(pathString)){
					features.add(metaPathFeature.randomWalk.get(pathString));
				}else{
					features.add((double) 0);
				}
				
				if(metaPathFeature.symmetricRandomWalk.containsKey(pathString)){
					features.add(metaPathFeature.symmetricRandomWalk.get(pathString));
				}else{
					features.add((double) 0);
				}
			}
		}else{
			for(int i=0;i<caseMetaPaths.size()*4;i++){
				features.add((double) 0);
			}
		}
		return features;
	}

}
