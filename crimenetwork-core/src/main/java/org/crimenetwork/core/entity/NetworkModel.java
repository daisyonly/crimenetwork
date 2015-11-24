package org.crimenetwork.core.entity;

import java.util.HashMap;
import java.util.HashSet;

public class NetworkModel {
    public HashMap<String, HashMap<String, String>> nodes;
    public HashSet<NetworkEdge> edges;
    
    public NetworkModel(){
    	this.nodes=new HashMap<String, HashMap<String, String>>();
    	this.edges=new HashSet<NetworkEdge>();
    }
}
