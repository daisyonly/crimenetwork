package org.crimenetwork.core.person;

import java.util.HashMap;

import org.crimenetwork.core.utility.ModelConvertor;
import org.crimenetwork.mongodb.entity.suspect.MSuspectBaseInfo;
import org.crimenetwork.mongodb.entity.suspect.SuspectCaseBaseInfo;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PersonProcessor {
	@Autowired
	@Qualifier("suspectDao")
	BasicRepository<MSuspectBaseInfo> suspectDao;
	
	public HashMap<String, HashMap<String, String>> findPersonToCase(Long cid) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		MSuspectBaseInfo anchor=suspectDao.findOneByAttributeName("cId", cid);
		for(SuspectCaseBaseInfo one:anchor.getCases()){
			res.put(one.getcId().toString(), ModelConvertor.transferSuspectCaseBaseInfo(one));
		}
		
		return res;
	}
	
	public HashMap<String, HashMap<String, String>> findPersonToPerson(Long cid) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		MSuspectBaseInfo anchor=suspectDao.findOneByAttributeName("cId", cid);
		
		return res;
	}

}
