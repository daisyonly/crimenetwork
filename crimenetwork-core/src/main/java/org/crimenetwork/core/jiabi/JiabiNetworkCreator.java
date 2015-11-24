package org.crimenetwork.core.jiabi;

import java.util.HashMap;
import java.util.List;

import org.crimenetwork.core.entity.NetworkModel;
import org.crimenetwork.mongodb.entity.currency.MJiabiBaseInfo;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class JiabiNetworkCreator {
	@Autowired
	private JiabiSimService jiabiSimService;
	
	@Autowired
	@Qualifier("jiabiDao")
	BasicRepository<MJiabiBaseInfo> jiabiDao;
	public NetworkModel name(Long fmid,double simThreshold) {
		HashMap<Long, MJiabiBaseInfo> jiabis=new HashMap<Long, MJiabiBaseInfo>();
		NetworkModel model=new NetworkModel();
		MJiabiBaseInfo anchor= jiabiDao.findOneByAttributeName("fmid", fmid);
		HashMap<String, Double> simJiabis= jiabiSimService.getSimJiabis(simThreshold, fmid);
		
		List<MJiabiBaseInfo> res=jiabiDao.findAllByAttributeName("guanzihao", anchor.getGuanzihao());
		return model;
	}

}
