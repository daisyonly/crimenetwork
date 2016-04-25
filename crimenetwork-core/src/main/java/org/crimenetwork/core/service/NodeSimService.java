package org.crimenetwork.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.crimenetwork.core.entity.NetworkModel;
import org.crimenetwork.core.nodesim.model.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("nodeSimService")
public class NodeSimService {
	
	@Autowired
	private DataGenerator dataGenerator;
	
	public void rank(NetworkModel networkModel){
		//char queryType=networkModel.queryId.charAt(0);
		List<Long> allSuspectNodes=getOneKindofNode(networkModel,'S');
		List<Long> allCaseNodes=getOneKindofNode(networkModel,'C');
		List<Long> allCmNodes=getOneKindofNode(networkModel,'J');
		
		List<SortNode> res = getSimList('S',networkModel.nearestSuspectNode,allSuspectNodes);
		updateNetworkModel(res, networkModel);
		
		res = getSimList('C',networkModel.nearestCaseNode,allCaseNodes);
		updateNetworkModel(res, networkModel);
		
		res = getSimList('J',networkModel.nearestCMNode,allCmNodes);
		updateNetworkModel(res, networkModel);
		
		adjustNetworModel(networkModel);
	}
	
	private void adjustNetworModel(NetworkModel networkModel) {
		for(String id:networkModel.nearestCaseNode){
			networkModel.nodes.get(id).put("size", 32+"");
		}
		for(String id:networkModel.nearestCMNode){
			networkModel.nodes.get(id).put("size", 32+"");
		}
		
		for(String id:networkModel.nearestSuspectNode){
			networkModel.nodes.get(id).put("size", 32+"");
		}	
		networkModel.nodes.get(networkModel.queryId).put("size", 35+"");		
	}

	private void updateNetworkModel(List<SortNode> res,
			NetworkModel networkModel) {
		if(res.size()==0) return;
		String sizeKey="size";
		int interval=20/res.size();
		double eps=1E-7;
		int curSize=30;
		networkModel.nodes.get(res.get(0).id).put(sizeKey, curSize+"");
		int equalCount=1;
		for(int i=1;i<res.size();i++){
			if(Math.abs(res.get(i).score-res.get(i-1).score)<eps){
				equalCount++;
				networkModel.nodes.get(res.get(i).id).put(sizeKey, curSize+"");
			}else{
				curSize=curSize-interval*equalCount;
				equalCount=1;
				networkModel.nodes.get(res.get(i).id).put(sizeKey, curSize+"");
			}
		}
	}

	
	
	private List<SortNode> getSimListFromRankingSVM(char flag,List<String> nearestNodes,List<Long> allSameNodes){
		Map<Long,Double> scoreMap=new HashMap<Long, Double>();
		for(String id:nearestNodes){
			Long curQueryId=Long.parseLong(id.substring(1));
			List<Long> candidates=new ArrayList<Long>(allSameNodes);
			candidates.remove(curQueryId);
			Map<Long,Map<String, Double>> data=dataGenerator.generateRankData(id,candidates);
			updateScoreMap(scoreMap,data);
		}
		List<SortNode> resList=new ArrayList<SortNode>();
		for(Map.Entry<Long,Double> entry:scoreMap.entrySet()){
			resList.add(new SortNode(flag+""+entry.getKey(), entry.getValue().doubleValue()));
		}
		
		Collections.sort(resList, new Comparator<SortNode>(){
				@Override
				public int compare(SortNode o1, SortNode o2) {				
					return (int) (o2.score-o1.score);
				}	
			});
		
		return resList;
		
	}
	
	
	/**
	 * 用规则的结果
	 * @param flag
	 * @param nearestNodes
	 * @param allSameNodes
	 * @return
	 */
	private List<SortNode> getSimList(char flag,List<String> nearestNodes,List<Long> allSameNodes){
		Map<Long,Double> scoreMap=new HashMap<Long, Double>();
		for(String id:nearestNodes){
			Long curQueryId=Long.parseLong(id.substring(1));
			List<Long> candidates=new ArrayList<Long>(allSameNodes);
			candidates.remove(curQueryId);
			Map<Long,Map<String, Double>> data=dataGenerator.generateRankData(id,candidates);
			updateScoreMap(scoreMap,data);
		}
		List<SortNode> resList=new ArrayList<SortNode>();
		for(Map.Entry<Long,Double> entry:scoreMap.entrySet()){
			resList.add(new SortNode(flag+""+entry.getKey(), entry.getValue().doubleValue()));
		}
		
		Collections.sort(resList, new Comparator<SortNode>(){
				@Override
				public int compare(SortNode o1, SortNode o2) {				
					return (int) (o2.score-o1.score);
				}	
			});
		
		return resList;
		
	}
    
	class SortNode{
		String id;
		double score;
		SortNode(String id,double score){
			this.id=id;
			this.score=score;
		}
	}
	private void updateScoreMap(Map<Long, Double> scoreMap,
			Map<Long, Map<String, Double>> data) {
		for(Map.Entry<Long, Map<String, Double>> entry:data.entrySet()){
			Long key=entry.getKey();
			double sum=getSum(entry.getValue());
			if(scoreMap.containsKey(key)){
				scoreMap.put(key, scoreMap.get(key)+sum);
			}else{
				scoreMap.put(key, sum);
			}
		}
		
	}

	private double getSum(Map<String, Double> map){
		double sum=0;
		for(Map.Entry<String, Double> entry:map.entrySet()){
			if(entry.getKey().indexOf('_')>0){
				String[] tmps=entry.getKey().split("_");
				int weight=10-tmps[1].length();
				sum+=entry.getValue()*weight;
			}else{
				sum+=entry.getValue();
			}
		}
		return sum;
	}
	
	private List<Long> getOneKindofNode(NetworkModel networkModel, char c) {
		Set<String> allNodeList=networkModel.nodes.keySet();
		List<Long> resList=new ArrayList<Long>();
		for(String id:allNodeList){
			if(id.charAt(0)==c)
				resList.add(Long.parseLong(id.substring(1)));
		}
		return resList;
	}

}
