package org.crimenetwork.core.nodesim;

import java.util.HashMap;
import java.util.Map;

import org.crimenetwork.core.metapath.MetaGraph;
import org.crimenetwork.core.metapath.TrieNode;
import org.crimenetwork.core.networkpath.PathSearcher;
import org.crimenetwork.core.networkpath.SearchResult;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;

public class MetaPathFeatureGenerator {
    
	private TrieNode caseTrieNode=null;
	private TrieNode suspectTrieNode=null;
	private TrieNode counterfeitMoneyTrieNode=null;
	
	//coding schema of pathcountSumDic key: J/C/S + id + "#" + path 
	private HashMap<String, Double> pathcountSumDic=null;
	
	private HashMap<String, HashMap<String, SearchResult>> searchResultMap=null;
	//coding schema of pathcountSumDic key: J/C/S + id of start node + "#" J/C/S + id of end node+"#" path
	//private HashMap<String, Double> pathcountNodeToNodeDic=null; 
	
	public MetaPathFeatureGenerator() {
		MetaGraph abGraph=MetaGraph.getAnInstance();
		suspectTrieNode=abGraph.getSuspectTrieNode();
		caseTrieNode=abGraph.getCaseTrieNode();
		counterfeitMoneyTrieNode=abGraph.getCounterfeitMoneyTrieNode();
		pathcountSumDic= new HashMap<String, Double>();
		//pathcountNodeToNodeDic = new HashMap<String, Double>();
		searchResultMap=new HashMap<String, HashMap<String, SearchResult>>();
	}
	
	private HashMap<String, SearchResult> getSearchResult(SuspectInfo query){
		String queryId="S"+query.getsId();
		if(searchResultMap.containsKey(queryId)){
			return searchResultMap.get(queryId);
		}else{
			HashMap<String, SearchResult> searchRes=PathSearcher.search(suspectTrieNode, query);
			searchResultMap.put(queryId, searchRes);
			return searchRes;
		}
	}
	
	private HashMap<String, SearchResult> getSearchResult(CounterfeitMoney query){
		String queryId="J"+query.getFmid();
		if(searchResultMap.containsKey(queryId)){
			return searchResultMap.get(queryId);
		}else{
			HashMap<String, SearchResult> searchRes=PathSearcher.search(counterfeitMoneyTrieNode, query);
			searchResultMap.put(queryId, searchRes);
			return searchRes;
		}
	}
	
	private HashMap<String, SearchResult> getSearchResult(CrimeCase query){
		String queryId="C"+query.getcId();
		if(searchResultMap.containsKey(queryId)){
			return searchResultMap.get(queryId);
		}else{
			HashMap<String, SearchResult> searchRes=PathSearcher.search(caseTrieNode, query);
			searchResultMap.put(queryId, searchRes);
			return searchRes;
		}
	}
	
	public HashMap<String, MetaPathFeature> generate(SuspectInfo query){		
		HashMap<String, SearchResult> searchRes=getSearchResult(query);
		String queryId="S"+query.getsId();
		return generate(searchRes,queryId);
	}
	
	public HashMap<String, MetaPathFeature> generate(CrimeCase query){		
		HashMap<String, SearchResult> searchRes=getSearchResult(query);
		String queryId="C"+query.getcId();		
		return generate(searchRes,queryId);
	}
	
	public HashMap<String, MetaPathFeature> generate(CounterfeitMoney query){		
		HashMap<String, SearchResult> searchRes=getSearchResult(query);
		String queryId="J"+query.getFmid();		
		return generate(searchRes,queryId);
	}
	
	private HashMap<String, MetaPathFeature> generate(HashMap<String, SearchResult> searchRes,String queryId){
		HashMap<String, MetaPathFeature> res=new HashMap<String, MetaPathFeature>();
		String queryIdPre=queryId +"#";
		updatePathcountSumDic(searchRes,queryIdPre);
		for(Map.Entry<String, SearchResult> entry:searchRes.entrySet()){	
			char flag=entry.getKey().charAt(0);
			
			if(flag=='C'){
				HashMap<String, SearchResult> tmp=getSearchResult(entry.getValue().crimeCase);
				String keyPre="C"+entry.getValue().crimeCase.getcId()+"#";
				updatePathcountSumDic(tmp,keyPre);
			}else if(flag=='J'){
				HashMap<String, SearchResult> tmp=getSearchResult(entry.getValue().counterfeitMoney);
				String keyPre="J"+entry.getValue().counterfeitMoney.getFmid()+"#";
				updatePathcountSumDic(tmp,keyPre);
			}else if(flag=='S'){
				HashMap<String, SearchResult> tmp=getSearchResult(entry.getValue().suspectInfo);
				String keyPre="S"+entry.getValue().suspectInfo.getsId()+"#";
				updatePathcountSumDic(tmp,keyPre);
			}		
		}
		
		for(Map.Entry<String, SearchResult> entry:searchRes.entrySet()){	
			String anchorId=entry.getKey();
			MetaPathFeature mf=new MetaPathFeature(queryId,anchorId);
			for(Map.Entry<String, Integer> one:entry.getValue().pathCount.entrySet()){
				String path=one.getKey();
				String reversePath=getReversePath(path);
				double pathCount=one.getValue();
				mf.pathCout.put(path, pathCount);
				
				double pathAll=pathcountSumDic.get(queryId+"#"+path);
				double revPathAll=pathcountSumDic.get(anchorId+"#"+reversePath);
			   
				double normalizedPathCount=pathCount*2/(pathAll+revPathAll);
				mf.normalizedPathCount.put(path, normalizedPathCount);	
				
				double randomWalk = pathCount/pathcountSumDic.get(queryId+"#"+path);
				mf.randomWalk.put(path, randomWalk);
				
				double reverRandomWalk = pathCount/pathcountSumDic.get(anchorId+"#"+reversePath);
				double symmetricRandomWalk = randomWalk+reverRandomWalk;
				mf.symmetricRandomWalk.put(path, symmetricRandomWalk);
			}
			res.put(anchorId, mf);
		}
		
		return res;
	}
	
	public void outputMetaPathFeature(HashMap<String, MetaPathFeature> features){
		for(Map.Entry<String, MetaPathFeature> entry: features.entrySet()){
			System.out.println(entry.getKey()+" ");
			for(Map.Entry<String, Double> one: entry.getValue().pathCout.entrySet()){
				System.out.print(one.getKey()+":"+one.getValue()+" ");
			}
			System.out.println();
			for(Map.Entry<String, Double> one: entry.getValue().normalizedPathCount.entrySet()){
				System.out.print(one.getKey()+":"+one.getValue()+" ");
			}
			System.out.println();
			for(Map.Entry<String, Double> one: entry.getValue().randomWalk.entrySet()){
				System.out.print(one.getKey()+":"+one.getValue()+" ");
			}
			System.out.println();
			for(Map.Entry<String, Double> one: entry.getValue().symmetricRandomWalk.entrySet()){
				System.out.print(one.getKey()+":"+one.getValue()+" ");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	private String getReversePath(String path){
		StringBuilder tmp=new StringBuilder(path);
		return tmp.reverse().toString();
	}
	
	private void updatePathcountSumDic(HashMap<String, SearchResult> searchRes,String keyPrex){
		HashMap<String, Double> tmp=new HashMap<String, Double>();
		for(Map.Entry<String, SearchResult> entry:searchRes.entrySet()){
			for(Map.Entry<String, Integer> one:entry.getValue().pathCount.entrySet()){
				if(tmp.containsKey(one.getKey())){
					tmp.put(one.getKey(), tmp.get(one.getKey())+1);
				}else{
					tmp.put(one.getKey(), (double) 1);
				}
			}
		}
		for(Map.Entry<String, Double> entry: tmp.entrySet()){
			String idString=keyPrex+entry.getKey();
			pathcountSumDic.put(idString, entry.getValue());
		}
	}
}
