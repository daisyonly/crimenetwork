package org.crimenetwork.core.networkpath;

import java.util.HashMap;

public class SearchResult {
	String queryNodeId; 
	String resultNodeId;
	HashMap<String, Integer> pathCount;
	
	public SearchResult(String queryNodeId,String resultNodeId){
		this.queryNodeId=queryNodeId;
		this.resultNodeId=resultNodeId;
		this.pathCount=new HashMap<String, Integer>();
	}

}
