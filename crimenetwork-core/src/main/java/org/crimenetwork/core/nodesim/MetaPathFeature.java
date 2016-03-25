package org.crimenetwork.core.nodesim;

import java.util.HashMap;

public class MetaPathFeature {
    String queryId;
	String anchorId;
    HashMap<String, Double> pathCout;
    HashMap<String, Double> normalizedPathCount;
    HashMap<String, Double> randomWalk;
    HashMap<String, Double> symmetricRandomWalk;
    public MetaPathFeature(String queryId,String anchorId){
    	this.anchorId=anchorId;
    	this.queryId=queryId;
    	pathCout=new HashMap<String, Double>();
    	normalizedPathCount=new HashMap<String, Double>();
    	randomWalk=new HashMap<String, Double>();
    	symmetricRandomWalk=new HashMap<String, Double>();
    }
}
