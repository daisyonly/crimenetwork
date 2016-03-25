package org.crimenetwork.core.networkpath;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.crimenetwork.core.metapath.MetaGraphNode;
import org.crimenetwork.core.metapath.TrieNode;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;


public class PathSearcher {

	public static HashMap<String, SearchResult> search(TrieNode root,CrimeCase cc){
		Queue<SearchNode> queue=new LinkedList<SearchNode>();
		SearchNode queryNode = new SearchNode( "1", root,"C");
		queryNode.crimeCase=cc;
		String queryNodeId="C"+cc.getcId();
		queue.offer(queryNode);
		return BFS(queue,queryNodeId,'C');
	}
	
	public static HashMap<String, SearchResult> search(TrieNode root,SuspectInfo si){
		Queue<SearchNode> queue=new LinkedList<SearchNode>();
		SearchNode queryNode = new SearchNode( "0", root,"S");
		queryNode.suspectInfo=si;
		String queryNodeId="S"+si.getsId();
		queue.offer(queryNode);
		return BFS(queue,queryNodeId,'S');
	}
	
	public static HashMap<String, SearchResult> search(TrieNode root,CounterfeitMoney cm){		
		Queue<SearchNode> queue=new LinkedList<SearchNode>();
		SearchNode queryNode = new SearchNode( "2", root,"J");
		queryNode.counterfeitMoney=cm;
		String queryNodeId="J"+cm.getFmid();
		queue.offer(queryNode);

		return BFS(queue,queryNodeId,'J');
	}
	
	private static HashMap<String, SearchResult> BFS(Queue<SearchNode> queue,String queryNodeId,char flag){
		HashMap<String, SearchResult> resHashMap=new HashMap<String, SearchResult>();
		while(!queue.isEmpty()){
			SearchNode peekNode=queue.poll();
			
			if(peekNode.paths.nodes[0]!=null){
				TrieNode paths=peekNode.paths.nodes[0];
				String curPath=peekNode.prePath+MetaGraphNode.PERSON;
				List<SearchNode> res=null;
				if(peekNode.flag.equals("C")){
					res = PathSearchHelper.search(peekNode.crimeCase, "S", curPath, paths);
					queue.addAll(res);
				}else if(peekNode.flag.equals("S")){
					res =PathSearchHelper.search(peekNode.suspectInfo, "S", curPath, paths);
					queue.addAll(res);
				}
				if(flag=='S'){
					for(SearchNode node:res){
						String idString="S"+node.suspectInfo.getsId();
						updateResHashMap(idString,resHashMap,queryNodeId,curPath,node.suspectInfo);
					}
				}
			}
			
			if(peekNode.paths.nodes[1]!=null){
				TrieNode paths=peekNode.paths.nodes[1];
				String curPath=peekNode.prePath+MetaGraphNode.CASES;
				List<SearchNode> res = null;
				if(peekNode.flag.equals("S")){
					res = PathSearchHelper.search(peekNode.suspectInfo, "C", curPath, paths);
					queue.addAll(res);
				}else if(peekNode.flag.equals("J")){
					res= PathSearchHelper.search(peekNode.counterfeitMoney, "C", curPath, paths);
					queue.addAll(res);
				}
				if(flag=='C'){
					for(SearchNode node:res){
						String idString="C"+node.crimeCase.getcId();
						updateResHashMap(idString,resHashMap,queryNodeId,curPath,node.crimeCase);
					}
				}
			}
			
			if(peekNode.paths.nodes[2]!=null){
				TrieNode paths=peekNode.paths.nodes[2];
				String curPath=peekNode.prePath+MetaGraphNode.JIABI;
				List<SearchNode> res = null;
				if(peekNode.flag.equals("C")){
					res = PathSearchHelper.search(peekNode.crimeCase, "J", curPath, paths);
					queue.addAll(res);
					
				}else if(peekNode.flag.equals("J")){
					res =PathSearchHelper.search(peekNode.counterfeitMoney, "J", curPath, paths);
					queue.addAll(res);
				}
				if(flag=='J'){
					for(SearchNode node:res){
						String idString="J"+node.counterfeitMoney.getFmid();
						updateResHashMap(idString,resHashMap,queryNodeId,curPath,node.counterfeitMoney);
					}
				}
			}
		}	
		return resHashMap;
	}
	
	private static void updateResHashMap(String resultNodeId, HashMap<String, SearchResult> resHashMap, 
			String queryNodeId, String curPath,CounterfeitMoney cm){
		if(resHashMap.containsKey(resultNodeId)){
			if(resHashMap.get(resultNodeId).pathCount.containsKey(curPath)){
				resHashMap.get(resultNodeId).pathCount.put(curPath, 
						resHashMap.get(resultNodeId).pathCount.get(curPath)+1);
			}else{
				resHashMap.get(resultNodeId).pathCount.put(curPath,1);
			}
		}else{
			SearchResult tmp=new SearchResult(queryNodeId,resultNodeId);
			tmp.pathCount.put(curPath, 1);
			tmp.counterfeitMoney=cm;
			resHashMap.put(resultNodeId, tmp);
		}
	}
	
	private static void updateResHashMap(String resultNodeId, HashMap<String, SearchResult> resHashMap, 
			String queryNodeId, String curPath,CrimeCase cc){
		if(resHashMap.containsKey(resultNodeId)){
			if(resHashMap.get(resultNodeId).pathCount.containsKey(curPath)){
				resHashMap.get(resultNodeId).pathCount.put(curPath, 
						resHashMap.get(resultNodeId).pathCount.get(curPath)+1);
			}else{
				resHashMap.get(resultNodeId).pathCount.put(curPath,1);
			}
		}else{
			SearchResult tmp=new SearchResult(queryNodeId,resultNodeId);
			tmp.pathCount.put(curPath, 1);
			tmp.crimeCase=cc;
			resHashMap.put(resultNodeId, tmp);
		}
	}
	
	private static void updateResHashMap(String resultNodeId, HashMap<String, SearchResult> resHashMap, 
			String queryNodeId, String curPath,SuspectInfo si){
		if(resHashMap.containsKey(resultNodeId)){
			if(resHashMap.get(resultNodeId).pathCount.containsKey(curPath)){
				resHashMap.get(resultNodeId).pathCount.put(curPath, 
						resHashMap.get(resultNodeId).pathCount.get(curPath)+1);
			}else{
				resHashMap.get(resultNodeId).pathCount.put(curPath,1);
			}
		}else{
			SearchResult tmp=new SearchResult(queryNodeId,resultNodeId);
			tmp.pathCount.put(curPath, 1);
			tmp.suspectInfo=si;
			resHashMap.put(resultNodeId, tmp);
		}
	}
}
