package org.crimenetwork.core.person;

import java.util.HashMap;

import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.repository.SuspectInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class PersonProcessor {
	
	@Autowired
	private SuspectInfoRepository suspectInfoRepository;
	
	public HashMap<String, HashMap<String, String>> findPersonToCase(Long sid) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		SuspectInfo anchor=suspectInfoRepository.findBySId(sid);
		
		
		return res;
	}
	
	public HashMap<String, HashMap<String, String>> findPersonToPerson(Long sid) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		SuspectInfo anchor=suspectInfoRepository.findBySId(sid);
		
		return res;
	}

}
