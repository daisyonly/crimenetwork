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
		SearchNode queryNode = new SearchNode( "", root,"C");
		queryNode.crimeCase=cc;
		String queryNodeId="C"+cc.getcId();
		queue.offer(queryNode);
		return BFS(queue,queryNodeId);
	}
	
	public static HashMap<String, SearchResult> search(TrieNode root,SuspectInfo si){
		Queue<SearchNode> queue=new LinkedList<SearchNode>();
		SearchNode queryNode = new SearchNode( "", root,"S");
		queryNode.suspectInfo=si;
		String queryNodeId="S"+si.getsId();
		queue.offer(queryNode);
		return BFS(queue,queryNodeId);
	}
	
	public static HashMap<String, SearchResult> search(TrieNode root,CounterfeitMoney cm){		
		Queue<SearchNode> queue=new LinkedList<SearchNode>();
		SearchNode queryNode = new SearchNode( "", root,"J");
		queryNode.counterfeitMoney=cm;
		String queryNodeId="J"+cm.getFmid();
		queue.offer(queryNode);

		return BFS(queue,queryNodeId);
	}
	
	private static HashMap<String, SearchResult> BFS(Queue<SearchNode> queue,String queryNodeId){
		HashMap<String, SearchResult> resHashMap=new HashMap<String, SearchResult>();
		while(!queue.isEmpty()){
			SearchNode peekNode=queue.poll();
			if(peekNode.paths.nodes[0]!=null){
				TrieNode paths=peekNode.paths.nodes[0];
				String curPath=peekNode.prePath+MetaGraphNode.PERSON;
				if(peekNode.flag.equals("C")){
					List<SearchNode> res = PathSearchHelper.search(peekNode.crimeCase, "S", curPath, paths);
					queue.addAll(res);
				}else if(peekNode.flag.equals("S")){
					queue.addAll(PathSearchHelper.search(peekNode.suspectInfo, "S", curPath, paths));
				}
			}
			
			if(peekNode.paths.nodes[1]!=null){
				TrieNode paths=peekNode.paths.nodes[1];
				String curPath=peekNode.prePath+MetaGraphNode.CASES;				
				if(peekNode.flag.equals("S")){
					List<SearchNode> res = PathSearchHelper.search(peekNode.suspectInfo, "C", curPath, paths);
					queue.addAll(res);
				}else if(peekNode.flag.equals("J")){
					queue.addAll(PathSearchHelper.search(peekNode.counterfeitMoney, "C", curPath, paths));
				}
			}
			
			if(peekNode.paths.nodes[2]!=null){
				TrieNode paths=peekNode.paths.nodes[2];
				String curPath=peekNode.prePath+MetaGraphNode.JIABI;
				List<SearchNode> res = null;
				if(peekNode.flag.equals("C")){
					res = PathSearchHelper.search(peekNode.crimeCase, "J", curPath, paths);
					queue.addAll(res);
					for(SearchNode node:res){
						String idString="J"+node.counterfeitMoney.getFmid();
						updateResHashMap(idString,resHashMap,queryNodeId,curPath);
					}
				}else if(peekNode.flag.equals("J")){
					res =PathSearchHelper.search(peekNode.counterfeitMoney, "J", curPath, paths);
					queue.addAll(res);
				}
				for(SearchNode node:res){
					String idString="J"+node.counterfeitMoney.getFmid();
					updateResHashMap(idString,resHashMap,queryNodeId,curPath);
				}
			}
		}	
		return resHashMap;
	}
	
	private static void updateResHashMap(String resultNodeId, HashMap<String, SearchResult> resHashMap, String queryNodeId, String curPath){
		if(resHashMap.containsKey(resultNodeId)){
			if(resHashMap.get(resultNodeId).pathCount.containsKey(curPath)){
				resHashMap.get(resultNodeId).pathCount.put(curPath, 
						resHashMap.get(resultNodeId).pathCount.get(resultNodeId)+1);
			}else{
				resHashMap.get(resultNodeId).pathCount.put(curPath,1);
			}
		}else{
			SearchResult tmp=new SearchResult(queryNodeId,resultNodeId);
			tmp.pathCount.put(curPath, 1);
			resHashMap.put(resultNodeId, tmp);
		}
	}
}
