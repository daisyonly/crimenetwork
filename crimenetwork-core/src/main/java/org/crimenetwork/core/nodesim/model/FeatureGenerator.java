package org.crimenetwork.core.nodesim.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.crimenetwork.core.nodesim.service.LabelHelper;
import org.crimenetwork.core.nodesim.service.LabelHelper.FeatureEntity;
import org.crimenetwork.core.utility.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataReader")
public class FeatureGenerator {
	@Autowired
	private LabelHelper labelHelper;
	private HashMap<String,HashMap<String, Integer>> labelData;
	
	public void chooseFunctionByType(String type,String inputFilePath,String trainFilePath,String orderFilePath, char flag){
		labelData=getLabelData(orderFilePath);
		if(type.equals("All")){
			generateALLFeature(inputFilePath,trainFilePath,orderFilePath,flag);
		}else if(type.equals("Attr")){
			generateAttrFeature(inputFilePath,trainFilePath,orderFilePath,flag);
		}else{
			generateTopoFeature(inputFilePath,trainFilePath,orderFilePath,flag);
		}
	}
	
    public HashMap<String,HashMap<String, Integer>> getLabelData(String orderFilePath) {
    	
		HashMap<String,HashMap<String, Integer>> labelDataMap=new HashMap<String,HashMap<String, Integer>>();
		
		FileUtil oraOrderFileUtil=new FileUtil(orderFilePath, "in", false);
		
		String orderLine=null;

		String curQueryId=null;
		while((orderLine=oraOrderFileUtil.readLine())!=null){
	
			if(orderLine.startsWith("#")){
				String tmps[]=orderLine.split(" ");
				curQueryId=tmps[tmps.length-1];
				HashMap<String, Integer> tmpHashMap =new HashMap<String, Integer>();
				labelDataMap.put(curQueryId, tmpHashMap);
				continue;
			}
			String[] tmpsStrings=orderLine.split(",");
			String id=tmpsStrings[0];
			int label=Integer.parseInt(tmpsStrings[1]);
			labelDataMap.get(curQueryId).put(id, label);
		}
		
		
		oraOrderFileUtil.close();
		return labelDataMap;
	
	}
	
	public void generateALLFeature(String inputFilePath,String trainFilePath,String orderFilePath, char flag){
		FileUtil trainFileUtil=new FileUtil(trainFilePath, "out", false);
		FileUtil orderFileUtil=new FileUtil(orderFilePath, "out", false);
		FileUtil inputFileUtil=new FileUtil(inputFilePath, "in", false);
		String line=null;
		int index=1;
		while((line=inputFileUtil.readLine())!=null){
			System.out.println("run:"+index+"   "+line);
			String[] ids=line.split(",");
			List<Long> candidates=new ArrayList<Long>();
			for(String id:ids){
				if(id.equals("")) continue;
				char curFlag=id.charAt(0);
				if(curFlag==flag){
					try {
						Long idLong = Long.parseLong(id.substring(1));
						candidates.add(idLong);
					} catch (Exception e) {
						System.out.println("error id:"+id);
					}
				}
			}
			if(candidates.size()<2) continue;
			String queryId=flag+""+candidates.get(0);
			candidates.remove(0);
			orderFileUtil.writeLine("# query "+index+": query id "+queryId);
			trainFileUtil.writeLine("# query "+index);
			List<FeatureEntity> resEntities=labelHelper.generateALLLabeldata(queryId, candidates);
			for(FeatureEntity entity: resEntities){
				orderFileUtil.writeLine(entity.id+","+entity.label);
				trainFileUtil.write(entity.label+" qid:"+index);
				int i=1;
				for(double one:entity.feature){
					trainFileUtil.write(" "+i+":"+one);
					++i;
				}
				trainFileUtil.writeLine("");
			}
			++index;
		}
		trainFileUtil.close();
		orderFileUtil.close();
		inputFileUtil.close();
		
	}
	
	
	public void generateAttrFeature(String inputFilePath,String trainFilePath,String orderFilePath, char flag){
		FileUtil trainFileUtil=new FileUtil(trainFilePath, "out", false);
		
		FileUtil inputFileUtil=new FileUtil(inputFilePath, "in", false);
		String line=null;
		int index=1;
		while((line=inputFileUtil.readLine())!=null){
			System.out.println("run:"+index+"   "+line);
			String[] ids=line.split(",");
			List<Long> candidates=new ArrayList<Long>();
			for(String id:ids){
				if(id.equals("")) continue;
				char curFlag=id.charAt(0);
				if(curFlag==flag){
					try {
						Long idLong = Long.parseLong(id.substring(1));
						candidates.add(idLong);
					} catch (Exception e) {
						System.out.println("error id:"+id);
					}
				}
			}
			if(candidates.size()<2) continue;
			String queryId=flag+""+candidates.get(0);
			candidates.remove(0);
			
			trainFileUtil.writeLine("# query "+index);
			Map<String, Integer> labelResult=labelData.get(queryId);
			List<FeatureEntity> resEntities=labelHelper.generateAttrLabeldata(queryId, candidates,labelResult);
			for(FeatureEntity entity: resEntities){
				
				trainFileUtil.write(entity.label+" qid:"+index);
				int i=1;
				for(double one:entity.feature){
					trainFileUtil.write(" "+i+":"+one);
					++i;
				}
				trainFileUtil.writeLine("");
			}
			++index;
		}
		trainFileUtil.close();
		
		inputFileUtil.close();
		
	}
	
	public void generateTopoFeature(String inputFilePath,String trainFilePath,String orderFilePath, char flag){
		FileUtil trainFileUtil=new FileUtil(trainFilePath, "out", false);
		
		FileUtil inputFileUtil=new FileUtil(inputFilePath, "in", false);
		String line=null;
		int index=1;
		while((line=inputFileUtil.readLine())!=null){
			System.out.println("run:"+index+"   "+line);
			String[] ids=line.split(",");
			List<Long> candidates=new ArrayList<Long>();
			for(String id:ids){
				if(id.equals("")) continue;
				char curFlag=id.charAt(0);
				if(curFlag==flag){
					try {
						Long idLong = Long.parseLong(id.substring(1));
						candidates.add(idLong);
					} catch (Exception e) {
						System.out.println("error id:"+id);
					}
				}
			}
			if(candidates.size()<2) continue;
			String queryId=flag+""+candidates.get(0);
			candidates.remove(0);
			
			trainFileUtil.writeLine("# query "+index);
			Map<String, Integer> labelResult=labelData.get(queryId);
			List<FeatureEntity> resEntities=labelHelper.generateTopoLabeldata(queryId, candidates,labelResult);
			for(FeatureEntity entity: resEntities){
				
				trainFileUtil.write(entity.label+" qid:"+index);
				int i=1;
				for(double one:entity.feature){
					trainFileUtil.write(" "+i+":"+one);
					++i;
				}
				trainFileUtil.writeLine("");
			}
			++index;
		}
		trainFileUtil.close();
		inputFileUtil.close();
		
	}
	
	
	public void test(String line,char flag){
		
		String[] ids=line.split(",");
		List<Long> candidates=new ArrayList<Long>();
		for(String id:ids){
			if(id.equals("")) continue;
			char curFlag=id.charAt(0);
			if(curFlag==flag){
				try {
					Long idLong = Long.parseLong(id.substring(1));
					candidates.add(idLong);
				} catch (Exception e) {
					System.out.println("error id:"+id);
				}
			}
		}
		String queryId=flag+""+candidates.get(0);
		candidates.remove(0);
		
		List<FeatureEntity> resEntities=labelHelper.generateALLLabeldata(queryId, candidates);
	    
	}

}
