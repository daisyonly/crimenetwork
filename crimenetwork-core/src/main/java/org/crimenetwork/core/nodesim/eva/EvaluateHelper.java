package org.crimenetwork.core.nodesim.eva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.crimenetwork.core.utility.FileUtil;

public class EvaluateHelper {
	public static void run(String testOrderFile,String predictionsPath){
		String evaOut="D:\\毕业设计\\svmrank\\data\\eva.txt";
		FileUtil testOrderFileUtil=new FileUtil(testOrderFile, "in", false);
		FileUtil evaOutFileUtil=new FileUtil(evaOut, "out", false);
		String line=null;
		List<List<Integer>> labeList=new ArrayList<List<Integer>>();
		List<Integer> tmpList=new ArrayList<Integer>();
		boolean first=true;
		while((line=testOrderFileUtil.readLine())!=null){
			if(first){
				first=false;
				continue;
			}
			if(line.startsWith("#")){
				labeList.add(new ArrayList<Integer>(tmpList));
				tmpList=new ArrayList<Integer>();
				continue;
			}
			String[] tmpsStrings=line.split(",");
			int label=Integer.parseInt(tmpsStrings[1]);
			tmpList.add(label);	
		}
		labeList.add(new ArrayList<Integer>(tmpList));
		testOrderFileUtil.close();
		FileUtil predictionsFile=new FileUtil(predictionsPath, "in", false);
		List<List<Double>> preList=new ArrayList<List<Double>>();
		for(List<Integer> list:labeList){
			List<Double> tmpList2=new ArrayList<Double>();
			for(int i:list){
				String lineString=predictionsFile.readLine();
				double pre=Double.parseDouble(lineString);
				tmpList2.add(pre);
			}
			preList.add(tmpList2);
		}
		predictionsFile.close();
		List<Map<Long, Integer>> correct=new ArrayList<Map<Long, Integer>>();
		List<Map<Long, Double>> predict=new ArrayList<Map<Long, Double>>();
		for(int index=0;index<preList.size();index++){
			Map<Long, Integer> corMap=new HashMap<Long, Integer>();
			Map<Long, Double> preMap=new HashMap<Long, Double>();
			for(int i=0;i<labeList.get(index).size();i++){
				corMap.put((long) i, labeList.get(index).get(i));
			}
			correct.add(corMap);
			for(int i=0;i<labeList.get(index).size();i++){
				preMap.put((long) i, preList.get(index).get(i));
			}
			predict.add(preMap);	
		}		
			
		double res=MapEvaluation.getMap(correct, predict);
		System.out.println("MAP"+res);
		evaOutFileUtil.writeLine("MAP"+" "+res);
		
		for(int nn=1;nn<=10;nn++){
			double rres=DCGUtil.getNNDCG(correct, predict, nn);
			System.out.println("NDCG@"+nn+" "+rres);
			evaOutFileUtil.writeLine("NDCG@"+nn+" "+rres);
		}
		evaOutFileUtil.close();
		
		
	}

}
