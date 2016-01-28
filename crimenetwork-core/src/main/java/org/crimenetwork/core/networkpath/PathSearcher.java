package org.crimenetwork.core.networkpath;

import java.util.HashMap;
import java.util.List;

import org.crimenetwork.core.jiabi.JiabiSimService;
import org.crimenetwork.core.metapath.MetaGraphNode;
import org.springframework.beans.factory.annotation.Autowired;

public class PathSearcher {
	@Autowired
	private JiabiSimService jiabiSimService;
	
	
	public void search(List<Integer> path,String id){
		
	}
	
	private List<HashMap<String, String>> find(int start,int end,String id){
		if(start==MetaGraphNode.JIABI&&end==MetaGraphNode.CASES){
			
		}else if (start==MetaGraphNode.CASES&&end==MetaGraphNode.JIABI) {
			
		}else if (start==MetaGraphNode.JIABI&&end==MetaGraphNode.CASES) {
			
		}else if (start==MetaGraphNode.PERSON&&end==MetaGraphNode.CASES) {
			
		}else if (start==MetaGraphNode.CASES&&end==MetaGraphNode.PERSON) {
			
		}else if (start==MetaGraphNode.JIABI&&end==MetaGraphNode.JIABI) {
			
		}else if (start==MetaGraphNode.PERSON&&end==MetaGraphNode.PERSON) {
			
		}
		
		return null;
		
	}

}
