package org.crimenetwork.core.nodesim.model;

import java.util.ArrayList;
import java.util.List;

import org.crimenetwork.core.nodesim.service.LabelHelper;
import org.crimenetwork.core.nodesim.service.LabelHelper.FeatureEntity;
import org.crimenetwork.core.utility.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataReader")
public class DataReader {
	@Autowired
	private LabelHelper labelHelper;
	
	public void readData(String inputFilePath,String trainFilePath,String orderFilePath, char flag){
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
			List<FeatureEntity> resEntities=labelHelper.generateLabeldata(queryId, candidates);
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
		
		List<FeatureEntity> resEntities=labelHelper.generateLabeldata(queryId, candidates);
	    
	}

}
