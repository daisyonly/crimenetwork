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
		nodeCount= 3;
		graph=new boolean[nodeCount][nodeCount];
		
		graph[MetaGraphNode.JIABI][MetaGraphNode.JIABI]=true;
		
		graph[MetaGraphNode.JIABI][MetaGraphNode.CASES]=true;
		graph[MetaGraphNode.CASES][MetaGraphNode.JIABI]=true;
		
		graph[MetaGraphNode.CASES][MetaGraphNode.PERSON]=true;
		graph[MetaGraphNode.PERSON][MetaGraphNode.CASES]=true;
		
		graph[MetaGraphNode.PERSON][MetaGraphNode.PERSON]=true;
		
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
				if(peek.size()>1&&peek.get(peek.size()-1)==end){
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
		
		return resFilter(res);	
	} 
	
	public TrieNode convertPathsToTree(List<List<Integer>> paths){
		TrieNode root=new TrieNode(paths.get(0).get(0));	
		for(List<Integer> path:paths){
			TrieNode curNode=root;
			for(int i=1;i<path.size();i++){
				if(curNode.nodes[path.get(i)]==null){
					TrieNode newNode=new TrieNode(path.get(i));
					curNode.nodes[path.get(i)]=newNode;
					curNode=newNode;
				}else{
					curNode=curNode.nodes[path.get(i)];
				}
			}
		}
		return root;
	}
	
	private List<List<Integer>> resFilter(List<List<Integer>> rawRes){
		List<List<Integer>> newResList=new ArrayList<List<Integer>>();
		for(List<Integer> one:rawRes){
			boolean delete=false;
			for(int i=0;i<one.size()-2;i++){
				if(one.get(i)==0&&one.get(i+1)==0&&one.get(i+2)==0){
					delete=true;
					break;
				}
			}
			if(!delete) newResList.add(one);
		}
		return newResList;
	}
	
	public static String getPathCode(List<Integer> path){
		StringBuilder ss=new StringBuilder();
		for(int i:path){
			ss.append(i);
		}
		return ss.toString();
	} 
	
	public static void main(String[] args) {
		MetaGraph abGraph=MetaGraph.getAnInstance();
		List<List<Integer>> res=abGraph.getMetaPath(5, 0, 0);
		for(List<Integer> list:res){
			System.out.println(list);
		}
		//TrieNode rootNode=abGraph.convertPathsToTree(res);
		System.out.println("hehe");
		
	}

}
