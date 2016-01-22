package org.crimenetwork.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.crimenetwork.dataextraction.utility.FileUtil;

public class BFS {
	HashMap<String, HashSet<String>> edges;
	HashSet<String> set;
	HashMap<Integer, List<List<String>>> networkCount;
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
		networkCount=new HashMap<Integer, List<List<String>>>();
	}
	public void run(String dataPath){
		init();
		
		String node=null;
		int max=0;
		while((node=getAnUnvisitedNode())!=null){		
			List<String> records=bfs(node);
			int key=records.size();
			if(max<key) max=key;
			if(networkCount.containsKey(key)){
				networkCount.get(key).add(records);
			}else{
				List<List<String>> tmp = new ArrayList<List<String>>();
				tmp.add(records);
				networkCount.put(key, tmp);
			}
		}
		System.out.println(max);
		FileUtil fileUtil=new FileUtil(dataPath, "out",false);
		for(Map.Entry<Integer, List<List<String>>> entry:networkCount.entrySet()){
			if(entry.getKey()==56) System.out.println("hahaha");
			if(entry.getKey()<10) continue;
			fileUtil.writeLine("##number##:"+ entry.getKey());
			for(List<String> list:entry.getValue()){
				for(String str: list){
					fileUtil.write(str+",");
				}
				fileUtil.newLine();
			}
		}
		fileUtil.close();
	}
	private List<String> bfs(String start){
		List<String> records= new ArrayList<String>();
		
		Queue<String> queue=new LinkedList<>();
		queue.add(start);
		set.remove(start);
		//int count=0;
		while(!queue.isEmpty()){
			String peek=queue.poll();
			records.add(peek);
			//count++;
			if(!edges.containsKey(peek)) continue;
			for(String node:edges.get(peek)){
				if(set.contains(node)) {
					queue.add(node);
					set.remove(node);
				}
			}
		}

		return records;
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
		insBfs.run("C:\\Users\\Daisy\\Desktop\\data.csv");
	}

}
