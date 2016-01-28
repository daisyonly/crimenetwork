package org.crimenetwork.core.cases;

import java.util.HashMap;


import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.repository.CrimeCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class CasesProcessor {
	@Autowired
	@Qualifier("caseDao")
	private CrimeCaseRepository crimeCaseRepository;
    
	
	public HashMap<String, HashMap<String, String>> findCaseToJiabi(Long cid) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		CrimeCase anchor=crimeCaseRepository.findByCId(cid);
		
		return res;
	}
	
	public HashMap<String, HashMap<String, String>> findCaseToPerson(Long cid) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		CrimeCase anchor=crimeCaseRepository.findByCId(cid);
		
		return res;
	}
}
