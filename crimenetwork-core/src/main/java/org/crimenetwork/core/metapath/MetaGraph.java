package org.crimenetwork.core.metapath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MetaGraph {

	private static volatile MetaGraph INSTANCE = null;
	private boolean graph[][];
    private int nodeCount;
	private MetaGraph() {
		nodeCount= 4;
		graph=new boolean[nodeCount][nodeCount];
		
		graph[MetaGraphNode.PERSON][MetaGraphNode.PERSON]=true;
		graph[MetaGraphNode.JIABI][MetaGraphNode.JIABI]=true;
		
		graph[MetaGraphNode.JIABI][MetaGraphNode.CASES]=true;
		graph[MetaGraphNode.CASES][MetaGraphNode.JIABI]=true;
		
		graph[MetaGraphNode.CASES][MetaGraphNode.PERSON]=true;
		graph[MetaGraphNode.PERSON][MetaGraphNode.CASES]=true;
		
		graph[MetaGraphNode.JIABI][MetaGraphNode.BANK]=true;
		graph[MetaGraphNode.BANK][MetaGraphNode.JIABI]=true;
		
		graph[MetaGraphNode.PERSON][MetaGraphNode.JIABI]=true;
		graph[MetaGraphNode.JIABI][MetaGraphNode.PERSON]=true;
	}

	public static MetaGraph getAnInstance() {
		if (INSTANCE == null) {
			synchronized (MetaGraph.class) {
				if (INSTANCE == null) {
					INSTANCE = new MetaGraph();
				}
			}
		}
		return INSTANCE;
	}
	class QueueNode{
		
	}
	
	public List<List<Integer>> getMetaPath(int length,int start, int end){
		List<List<Integer>> res=new ArrayList<List<Integer>>();
		Queue<List<Integer>> queue=new LinkedList<List<Integer>>();
		List<Integer> startList=new ArrayList<Integer>();
		startList.add(start);
		queue.offer(new ArrayList<Integer>(startList));
		int curLength=0;
		
		while(curLength<length&&queue.size()!=0){
			int count=queue.size();
			while(count-->0){
				List<Integer> peek=queue.poll();
				int lastNode=peek.get(peek.size()-1);
				if(lastNode==end){
					res.add(peek);
				}
				for(int i=0;i<nodeCount;i++){
					if(graph[lastNode][i]){
						List<Integer> tmp=new ArrayList<Integer>(peek);
						tmp.add(i);
						queue.add(tmp);
					}
				}
			}						
			curLength++;
		}				
		return res;	
	} 
	public static void main(String[] args) {
		MetaGraph abGraph=MetaGraph.getAnInstance();
		List<List<Integer>> res=abGraph.getMetaPath(4, 2, 0);
		for(List<Integer> list:res){
			System.out.println(list);
		}
		
	}

}
