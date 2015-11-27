package org.crimenetwork.core.networkpath;

import java.util.HashMap;
import java.util.List;

import org.crimenetwork.core.jiabi.JiabiSimService;
import org.crimenetwork.core.metapath.MetaGraphNode;
import org.crimenetwork.mongodb.entity.currency.MJiabiBaseInfo;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PathSearcher {
	@Autowired
	private JiabiSimService jiabiSimService;
	
	@Autowired
	@Qualifier("jiabiDao")
	BasicRepository<MJiabiBaseInfo> jiabiDao;
	
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
