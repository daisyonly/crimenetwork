package org.crimenetwork.dataextraction.nameDisambiguation.clusterService;

import java.util.ArrayList;

import org.crimenetwork.dataextraction.nameDisambiguation.attribute.SimilarCalculator;
import org.crimenetwork.dataextraction.nameDisambiguation.attribute.StrongRulesProcessor;
import org.crimenetwork.dataextraction.nameDisambiguation.data.SuspectDataHelper;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("clusterProcessor")
public class ClusterProcessor {
	public static final double QUITE_SILILAR_THRESHOLD = 0.5;
	public static final double SILILAR_THRESHOLD = 0.2;
	
	@Autowired
	@Qualifier("suspectDataHelper")
	private SuspectDataHelper suspectDataHelper;
	
	
	public ArrayList<ArrayList<SuspectBaseInfo>> process(int dataIndex){
		ArrayList<SuspectBaseInfo> rawData=suspectDataHelper.getDataByGroupIndex(dataIndex);
		ArrayList<ArrayList<SuspectBaseInfo>> clusters=new ArrayList<ArrayList<SuspectBaseInfo>>();
		
		//先处理强规则
		for(SuspectBaseInfo sbi:rawData){
			int index=-1;
			for(int i=0;i<clusters.size();i++){				
			  if(StrongRulesProcessor.isTheSameOne(clusters.get(i),sbi)){
				  index=i;
				  break;
			  }		  
			}
			if(index==-1){
				ArrayList<SuspectBaseInfo> newCluster=new ArrayList<SuspectBaseInfo>();
				newCluster.add(sbi);
				clusters.add(newCluster);
			}else{
				clusters.get(index).add(sbi);
			}
		}
		
		//将非常相似的两个类先合并
		boolean flag=true;
		while(flag){
			int index1=-1,index2=-1;
			for(int i=0;i<clusters.size();i++){
				boolean isFound=false;
				for(int j=i+1;j<clusters.size();j++){			
					if(SimilarCalculator.computeClusterSimilar(clusters.get(i), clusters.get(j))
							>QUITE_SILILAR_THRESHOLD){
						index1=i;
						index2=j;
						isFound=true;
						break;
					}
				}
				if(isFound) break;
			}
			if(index1==-1) flag=false;
			else{
				ArrayList<SuspectBaseInfo> newCluster=new ArrayList<SuspectBaseInfo>();
				newCluster.addAll(clusters.get(index1));
				newCluster.addAll(clusters.get(index2));
				clusters.remove(index2);
				clusters.remove(index1);
				clusters.add(newCluster);
			}
		}
		
		double curSim=Double.MAX_VALUE;
		int curCluster1=-1,curCluster2=-1;
		while(curSim>SILILAR_THRESHOLD){
			//System.out.println("sim:"+curSim);
			if(curCluster1!=-1){
				ArrayList<SuspectBaseInfo> newCluster=new ArrayList<SuspectBaseInfo>();
				newCluster.addAll(clusters.get(curCluster1));
				newCluster.addAll(clusters.get(curCluster2));
				clusters.remove(curCluster2);
				clusters.remove(curCluster1);
				clusters.add(newCluster);
			}
			
			curSim=Double.MIN_VALUE;
			//System.out.println("sim:"+curSim);
			for(int i=0;i<clusters.size();i++){
				for(int j=i+1;j<clusters.size();j++){
					double tmpSim=SimilarCalculator.computeClusterSimilar(clusters.get(i), clusters.get(j));
					if(tmpSim>curSim){
						curCluster1=i;
						curCluster2=j;
						curSim=tmpSim;
					}
				}					
			}
			System.out.println("sim:"+curSim);
		}
		return clusters;
		
	}
	
	public void outputCluster(ArrayList<ArrayList<SuspectBaseInfo>> clusters){
		for(int i=0;i<clusters.size();i++){
			System.out.println("cluster "+i+" :");
			for(SuspectBaseInfo one:clusters.get(i)){
				System.out.println(one);
			}
			System.out.println();
		}
	}

}
