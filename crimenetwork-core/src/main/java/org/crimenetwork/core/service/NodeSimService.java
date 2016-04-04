package org.crimenetwork.core.service;

import java.util.ArrayList;
import java.util.List;
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
		char queryType=networkModel.queryId.charAt(0);
		List<Long> allSuspectNodes=getOneKindofNode(networkModel,'S');
		List<Long> allCaseNodes=getOneKindofNode(networkModel,'C');
		List<Long> allCmNodes=getOneKindofNode(networkModel,'J');
		for(String id:networkModel.nearestSuspectNode){
			
		}
	}

	private List<Long> getOneKindofNode(NetworkModel networkModel, char c) {
		Set<String> allNodeList=networkModel.nodes.keySet();
		List<Long> resList=new ArrayList<Long>();
		for(String id:allNodeList){
			if(id.charAt(0)=='c')
				resList.add(Long.parseLong(id.substring(1)));
		}
		return resList;
	}

}
