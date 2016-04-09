package org.crimenetwork.dataextraction.nodeSim;

import java.util.HashMap;

public class NetworkEdge {
    public  String fromId;
    public  String endId;
    public  HashMap<String, String> attributes;
    
    public NetworkEdge(String fromId,String endId){
    	this.fromId=fromId;
    	this.endId=endId;
    	this.attributes=new HashMap<String, String>();
    }
    
    @Override
  	public int hashCode() {
  		return fromId.hashCode()*31+endId.hashCode();
  	}
  	
  	@Override
  	public boolean equals(Object obj) {
  		if (!(obj instanceof NetworkEdge))
              return false;
          if (obj == this)
              return true;

          NetworkEdge rhs = (NetworkEdge) obj;
          if((rhs.fromId.equals(this.fromId)&&rhs.endId.equals(this.endId))
          		||(rhs.endId.equals(this.fromId)&&rhs.fromId.equals(this.endId)))
          	return true;
          return false;
  	}
}
