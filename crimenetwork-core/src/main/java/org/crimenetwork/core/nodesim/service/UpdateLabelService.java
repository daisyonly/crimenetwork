package org.crimenetwork.core.nodesim.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;




import java.util.Map;
import java.util.Set;

import org.crimenetwork.core.nodesim.SimRankFeatureGenerator;
import org.crimenetwork.core.utility.FileUtil;
import org.springframework.stereotype.Service;

@Service("updateLabelService")
public class UpdateLabelService {
	private SimRankFeatureGenerator simRankFeatureGenerator=new SimRankFeatureGenerator();

	
	public void update(char flag,String oraDataFile,String oraOrderFile, String dataFile,String orderFile) {
    	FileUtil oraDataFileUtil=new FileUtil(oraDataFile, "in", false);
		FileUtil dataFileUtil=new FileUtil(dataFile, "out", false);
		
		FileUtil oraOrderFileUtil=new FileUtil(oraOrderFile, "in", false);
		FileUtil orderFileUtil=new FileUtil(orderFile, "out", false);
		
		String orderLine=null;
		String dataLine=null;
		List<List<LabelNode>> labeList =new ArrayList<List<LabelNode>>();
		List<List<DataNode>> DataList =new ArrayList<List<DataNode>>();
		List<String> queryIds=new ArrayList<String>();
		
		List<LabelNode> tmpLabelNodes=new ArrayList<LabelNode>();
		List<DataNode> tmpDataNodes=new ArrayList<DataNode>();
		boolean first=true;
		while((orderLine=oraOrderFileUtil.readLine())!=null&&
				(dataLine=oraDataFileUtil.readLine())!=null){
			if(first){
				first=false;
				String tmps[]=orderLine.split(":");
				queryIds.add(tmps[1]);
				continue;
			}
			if(orderLine.startsWith("#")){
				labeList.add(new ArrayList<LabelNode>(tmpLabelNodes));
				DataList.add(new ArrayList<DataNode>(tmpDataNodes));
				tmpLabelNodes=new ArrayList<LabelNode>();
				tmpDataNodes=new ArrayList<DataNode>();
				continue;
			}
			LabelNode labelNode=new LabelNode(orderLine);
			tmpLabelNodes.add(labelNode);
			tmpDataNodes.add(new DataNode(labelNode.id,dataLine, flag));
		}
		labeList.add(new ArrayList<LabelNode>(tmpLabelNodes));
		DataList.add(new ArrayList<DataNode>(tmpDataNodes));
		for(int i=0;i<DataList.size();i++){
			Map<String, Integer> ranks=getRank(flag,DataList.get(i));
			for(DataNode node:DataList.get(i)){
				node.label=ranks.get(node.id);
			}
			for(LabelNode node:labeList.get(i)){
				node.label=ranks.get(node.id);
			}
		}
		
		for(int index=0;index<DataList.size();index++){
			int curIndex=index+1;
			orderFileUtil.writeLine("# query "+curIndex+":"+queryIds.get(0));
			dataFileUtil.writeLine("# query "+curIndex);
			for(DataNode node:DataList.get(index)){
				dataFileUtil.write(node.label+" qid:"+curIndex);
				int i=1;
				for(double one:node.features){
					dataFileUtil.write(" "+i+":"+one);
					++i;
				}
				dataFileUtil.writeLine("");
			}
			for(LabelNode node:labeList.get(index)){
				orderFileUtil.writeLine(node.id+","+node.label);
			}
		}
		
		oraDataFileUtil.close();
		dataFileUtil.close();
		
		oraOrderFileUtil.close();
		orderFileUtil.close();
	}
	
	private Map<String, Integer> getRank(char flag,List<DataNode> list){
		List<DataNode> curDataNodes =new ArrayList<DataNode>(list);
		Collections.sort(curDataNodes, new Comparator<DataNode>(){
			@Override
			public int compare(DataNode o1, DataNode o2) {				
				return (int) (o1.sum-o2.sum);
			}	
		});
		Map<String, Integer> resMap=new HashMap<String, Integer>();
		int curRank=0;
		resMap.put(curDataNodes.get(0).id, curRank);
		for(int i=1;i<curDataNodes.size();i++){
			if(Math.abs(curDataNodes.get(i).sum-curDataNodes.get(i-1).sum)>0.00001){
				curRank++;
			}
			resMap.put(curDataNodes.get(i).id, curRank);
		}
		boolean update=true;
		double min=curDataNodes.get(0).sum;
		if(flag=='C'){
			if(min<21) update=false;
			
		}else if(flag=='J'){
			if(min<10) update=false;		
		}else{	
			if(min<25) update=false;		
		}
		
		if(update&&curRank==0){
			Set<String> resMapKey=resMap.keySet();
			for(String key:resMapKey){
				resMap.put(key, 1);
			}
		}
		return resMap;
	}
    
    private  double getSum(char flag, List<Double> feature){
		List<String> header=null;
		if(flag=='S'){
			header=simRankFeatureGenerator.suspectFeatureHeader;
		}else if(flag=='J'){
			header=simRankFeatureGenerator.counterfeitMoneyFeatureHeader;
		}else{
			header=simRankFeatureGenerator.caseFeatureHeader;
		}
		double sum=0;
		for(int i=0;i<header.size();i++){
			if(header.get(i).indexOf('_')>0){
				String[] tmps=header.get(i).split("_");
				int weight=10-tmps[1].length();
				sum+=feature.get(i)*weight;
			}else{
				sum+=feature.get(i);
			}
		}
		return sum;
	}
    
    public class DataNode{
    	public int label;
    	public String id;
    	public List<Double> features;
    	double sum;
    	public DataNode(String id,String line,char flag){
    		this.id=id;
    		features =new ArrayList<Double>();
    		int indexOne=line.indexOf(':');
    		String dataString=line.substring(indexOne-1);
    		String[] rawfeatures=dataString.split(" ");
    		for(String rawfeature:rawfeatures){
    			String tmprawfeature=rawfeature.trim();
    			if(tmprawfeature.equals("")) continue;
    			String[] tmps=tmprawfeature.split(":");
    			//int key=Integer.parseInt(tmps[0]);
    			double value=Double.parseDouble(tmps[1]);
    			features.add(value);
    		}
    		this.sum=getSum(flag,features);	
    	}
    	
    }
    
    public class LabelNode{
    	public String id;
    	public int label;
    	public LabelNode(String line){
    		String[] tmpsStrings=line.split(",");
			this.id=tmpsStrings[0];
    	}
    	
    	@Override
    	public String toString(){
    		return this.id+","+this.label;
    	}
    }
}
