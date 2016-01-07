package org.crimenetwork.currencysim;

import java.util.HashMap;
import java.util.Map;

public class MapHelper {
	

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
