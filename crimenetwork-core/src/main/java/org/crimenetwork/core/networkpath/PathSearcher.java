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

	
	
	public void search(List<Integer> path,String id){
		
	}
	
	public List<HashMap<String, String>> find(int start,int end,String id){
		if(start==MetaGraphNode.JIABI&&end==MetaGraphNode.CASES){
			
		}else if (start==MetaGraphNode.CASES&&end==MetaGraphNode.JIABI) {
			
		}else if (start==MetaGraphNode.JIABI&&end==MetaGraphNode.CASES) {
			
		}else if (start==MetaGraphNode.PERSON&&end==MetaGraphNode.CASES) {
			
		}else if (start==MetaGraphNode.CASES&&end==MetaGraphNode.PERSON) {
			
		}else if (start==MetaGraphNode.JIABI&&end==MetaGraphNode.JIABI) {
			
		}else if (start==MetaGraphNode.PERSON&&end==MetaGraphNode.PERSON) {
			
		}
		
		return null;
		
	}
	
	public HashMap<String, SearchResult> search(TrieNode root,CounterfeitMoney cm){
		HashMap<String, SearchResult> resHashMap=new HashMap<String, SearchResult>();
		
		Queue<SearchNode> queue=new LinkedList<SearchNode>();
		queue.offer(new SearchNode( "", root));
		
		while(!queue.isEmpty()){
			SearchNode peekNode=queue.poll();
			
			if(peekNode.paths.nodes[0]!=null){
				
			}
			
			if(peekNode.paths.nodes[1]!=null){
				
			}
			
			if(peekNode.paths.nodes[2]!=null){
				
			}
		}
		
		return resHashMap;
	}
	
	public void search(List<Integer> path,CrimeCase cc){
		
	}
	
	public void search(List<Integer> path,SuspectInfo cm){
		
	}

}
