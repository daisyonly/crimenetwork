package org.crimenetwork.core.cases;

import java.util.HashMap;

import org.crimenetwork.core.utility.ModelConvertor;
import org.crimenetwork.mongodb.entity.cases.CaseSuspectBaseInfo;
import org.crimenetwork.mongodb.entity.cases.MCaseBaseInfo;
import org.crimenetwork.mongodb.entity.currency.BaseJiabiInfo;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class CasesProcessor {
	@Autowired
	@Qualifier("caseDao")
	BasicRepository<MCaseBaseInfo> caseDao;
    
	
	public HashMap<String, HashMap<String, String>> findCaseToJiabi(Long cmid) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		MCaseBaseInfo anchor=caseDao.findOneByAttributeName("cmid", cmid);
		for(BaseJiabiInfo one: anchor.getCounterfeitMoneys()){
			res.put(one.getFmid().toString(), ModelConvertor.transferBaseJiabiInfo(one));
		}
		return res;
	}
	
	public HashMap<String, HashMap<String, String>> findCaseToPerson(Long cmid) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		MCaseBaseInfo anchor=caseDao.findOneByAttributeName("cmid", cmid);
		for(CaseSuspectBaseInfo one: anchor.getSuspects()){
			res.put(one.getsId().toString(), ModelConvertor.transferCaseSuspectBaseInfo(one));
		}
		return res;
	}
}
