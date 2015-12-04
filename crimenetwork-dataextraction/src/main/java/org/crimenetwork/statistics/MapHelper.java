package org.crimenetwork.statistics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MapHelper {
	public static void write(HashMap<String, HashSet<String>> edges,String path) {
		FileUtil fileUtil=new FileUtil(path, "out",false);
		for(Map.Entry<String, HashSet<String>> entry: edges.entrySet()){
			String start=entry.getKey();
			for(String end:entry.getValue()){
				fileUtil.writeLine(start+" "+end);
			}
		}
		fileUtil.close();
	}
	
	public static HashMap<String, HashSet<String>> read(String path) {
		FileUtil fileUtil=new FileUtil(path, "in",false);
		HashMap<String, HashSet<String>> edges=new HashMap<String, HashSet<String>>();
		String line=null;
		int linecount=0;
		while((line=fileUtil.readLine())!=null){
			String[] nodes=line.split(" ");
			if(nodes.length!=2) break;
			linecount++;
			if(edges.containsKey(nodes[0])){
				edges.get(nodes[0]).add(nodes[1]);
			}else {
				HashSet<String> tmp=new HashSet<String>();
				tmp.add(nodes[1]);
				edges.put(nodes[0], tmp);
			}
		}
		System.out.println(linecount);
		fileUtil.close();
		return edges;
	}
	
	public static HashMap<String, HashSet<String>> read(String paths[]) {		
		HashMap<String, HashSet<String>> edges=new HashMap<String, HashSet<String>>();
		String line=null;
		int linecount=0;
		for(String path:paths){
			FileUtil fileUtil=new FileUtil(path, "in",false);
			while((line=fileUtil.readLine())!=null){
				String[] nodes=line.split(" ");
				if(nodes.length!=2) break;
				linecount++;
				if(edges.containsKey(nodes[0])){
					edges.get(nodes[0]).add(nodes[1]);
				}else {
					HashSet<String> tmp=new HashSet<String>();
					tmp.add(nodes[1]);
					edges.put(nodes[0], tmp);
				}
			}
			System.out.println(linecount);
			fileUtil.close();
		}
		return edges;
	}
	public static void main(String[] args) {
		HashMap<String, HashSet<String>> edges=
			MapHelper.read("C:\\Users\\Daisy\\Desktop\\mapS.txt");
		for(Map.Entry<String, HashSet<String>> entry: edges.entrySet()){
			String start=entry.getKey();
			for(String end:entry.getValue()){
				if(!edges.containsKey(end)){
					System.out.println(start+"+"+end+": end not have edge");
				}else{
					if(!edges.get(end).contains(start)){
						System.out.println(start+"+"+end+": end not in edge");
					}
				}
			}
		}
		System.out.println("end!!");
	}

	public static void write(String start,HashMap<String, HashMap<Integer, Double>> result,
			String dataPath) {
		FileUtil fileUtil=new FileUtil(dataPath, "out",true);
		for(Map.Entry<String, HashMap<Integer, Double>> entry: result.entrySet()){
			String end=entry.getKey();
			fileUtil.writeLine(start+" "+end);
			for(Map.Entry<Integer, Double> type:entry.getValue().entrySet()){
				fileUtil.writeLine(type.getKey()+" "+type.getValue());
			}
		}
		fileUtil.close();
		
	}
	

}
