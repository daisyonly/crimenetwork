package org.crimenetwork.core.jiabi;

import java.util.HashMap;

import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class JiabiProcessor {
	@Autowired
	private JiabiSimService jiabiSimService;
	
	@Autowired
	@Qualifier("jiabiDao")
	private CounterfeitMoneyRepository counterfeitMoneyRepository;
	
	@SuppressWarnings("unused")
	public HashMap<String, HashMap<String, String>> findJiabiToJiabi(Long fmid, double simThreshold) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		
		CounterfeitMoney anchor = counterfeitMoneyRepository.findByFmid(fmid);
		HashMap<String, Double> simJiabis= jiabiSimService.getSimJiabis(simThreshold, fmid);
		
		
		
		return res;
	}
	
	@SuppressWarnings("unused")
	public HashMap<String, HashMap<String, String>> findJiabiToCase(Long fmid, double simThreshold) {
		HashMap<String, HashMap<String, String>> res=
				new HashMap<String, HashMap<String, String>>();
		
		CounterfeitMoney anchor = counterfeitMoneyRepository.findByFmid(fmid);
		
		return res;
	}
	
	

}
