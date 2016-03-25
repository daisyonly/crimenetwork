package org.crimenetwork.core.networkpath;

import java.util.HashMap;

import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;

public class SearchResult {
	String queryNodeId; 
	String resultNodeId;
	public HashMap<String, Integer> pathCount;
	
	public CounterfeitMoney counterfeitMoney;
	public CrimeCase crimeCase;
	public SuspectInfo suspectInfo;
	
	public SearchResult(String queryNodeId,String resultNodeId){
		this.queryNodeId=queryNodeId;
		this.resultNodeId=resultNodeId;
		this.pathCount=new HashMap<String, Integer>();
	}

}
