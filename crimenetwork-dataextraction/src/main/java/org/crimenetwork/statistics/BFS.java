package org.crimenetwork.statistics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BFS {
	HashMap<String, HashSet<String>> edges;
	HashSet<String> set;
	int[] networkCount;
	public BFS(HashMap<String, HashSet<String>> edges){
		this.edges=edges;
	}
	private void init(){
		set=new HashSet<String>();
		for(Map.Entry<String, HashSet<String>> entry: edges.entrySet()){
			String start=entry.getKey();
			if(!set.contains(start)) set.add(start);
			for(String end:entry.getValue()){
				if(!set.contains(end)) set.add(end);
			}
		}
		networkCount=new int[1000];
	}
	public void run(){
		init();
		
		String node=null;
		while((node=getAnUnvisitedNode())!=null){
			
			int count=bfs(node);
			networkCount[count]++;
		}
		for(int i=0;i<1000;i++){
			if(networkCount[i]!=0){
				System.out.println(i+" : " + networkCount[i]);
			}
		}
	}
	private int bfs(String start){
		Queue<String> queue=new LinkedList<>();
		queue.add(start);
		set.remove(start);
		int count=0;
		while(!queue.isEmpty()){
			String peek=queue.poll();
			count++;
			if(!edges.containsKey(peek)) continue;
			for(String node:edges.get(peek)){
				if(set.contains(node)) {
					queue.add(node);
					set.remove(node);
				}
			}
		}

		return count;
	}
	
	
	private String getAnUnvisitedNode(){
		Iterator<String> it = set.iterator();
		if(it.hasNext()) return it.next();
		return null;
	}
	public static void main(String[] args) {
		String paths[]=new String[3];
		paths[0]="C:\\Users\\Daisy\\Desktop\\map.txt";
		paths[1]="C:\\Users\\Daisy\\Desktop\\mapS.txt";
		paths[2]="C:\\Users\\Daisy\\Desktop\\mapC.txt";
		//HashMap<String, HashSet<String>> edges=
			//	MapHelper.read("C:\\Users\\Daisy\\Desktop\\mapS.txt");
		HashMap<String, HashSet<String>> edges=
				MapHelper.read(paths);
		BFS insBfs=new BFS(edges);
		insBfs.run();
	}

}
