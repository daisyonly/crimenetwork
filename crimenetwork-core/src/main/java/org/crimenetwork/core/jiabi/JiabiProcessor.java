package org.crimenetwork.core.jiabi;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.crimenetwork.core.utility.ModelConvertor;
import org.crimenetwork.mongodb.entity.currency.JiabiCaseBaseInfo;
import org.crimenetwork.mongodb.entity.currency.MJiabiBaseInfo;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class JiabiProcessor {
	@Autowired
	private JiabiSimService jiabiSimService;
	
	@Autowired
	@Qualifier("jiabiDao")
	BasicRepository<MJiabiBaseInfo> jiabiDao;
	
	public HashMap<String, HashMap<String, String>> findJiabiToJiabi(Long fmid, double simThreshold) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		
		MJiabiBaseInfo anchor= jiabiDao.findOneByAttributeName("fmid", fmid);
		HashMap<String, Double> simJiabis= jiabiSimService.getSimJiabis(simThreshold, fmid);
		
		for(Entry<String, Double> entry: simJiabis.entrySet()){
			MJiabiBaseInfo one= jiabiDao.findOneByAttributeName("fmid", entry.getKey());
		    res.put(entry.getKey(), ModelConvertor.transferMJiabiBaseInfo(one));
		}
		List<MJiabiBaseInfo> guanzihaoJiabis=jiabiDao.findAllByAttributeName("guanzihao", anchor.getGuanzihao());
		for(MJiabiBaseInfo one: guanzihaoJiabis){		
		    res.put(one.getFmid().toString(), ModelConvertor.transferMJiabiBaseInfo(one));
		}
		
		return res;
	}
	
	public HashMap<String, HashMap<String, String>> findJiabiToCase(Long fmid, double simThreshold) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		
		MJiabiBaseInfo anchor= jiabiDao.findOneByAttributeName("fmid", fmid);
		Set<JiabiCaseBaseInfo> jcbis=anchor.getCaseInfos();
		for(JiabiCaseBaseInfo one:jcbis){
			res.put(one.getcId().toString(), ModelConvertor.transferJiabiCaseBaseInfo(one));
		}
		return res;
	}
	
	

}
