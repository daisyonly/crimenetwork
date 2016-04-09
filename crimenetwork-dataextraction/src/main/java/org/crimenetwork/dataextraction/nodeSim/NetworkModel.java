package org.crimenetwork.dataextraction.nodeSim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class NetworkModel {
	public String queryId;
    public HashMap<String, HashMap<String, String>> nodes;
    public HashSet<NetworkEdge> edges;
    public List<String> nearestCaseNode;
    public List<String> nearestCMNode;
    public List<String> nearestSuspectNode;
    public int caseLevel=-1;
    public int cMLevel=-1;
    public int suspectLevel=-1;
    
    public NetworkModel(){
    	this.nodes=new HashMap<String, HashMap<String, String>>();
    	this.edges=new HashSet<NetworkEdge>();
    	this.nearestCaseNode=new ArrayList<String>();
    	this.nearestCMNode=new ArrayList<String>();
    	this.nearestSuspectNode=new ArrayList<String>();
    	
    }
}
