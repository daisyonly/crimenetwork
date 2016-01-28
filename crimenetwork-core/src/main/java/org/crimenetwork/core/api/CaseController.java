package org.crimenetwork.core.api;


import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.repository.SuspectInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class CaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(CaseController.class);
	
	@Autowired
	private SuspectInfoRepository suspectInfoRepository;

	@RequestMapping(value = "/case", method = RequestMethod.GET)
	@ResponseBody
	public SuspectInfo loadScriptContent() {
		logger.debug("here!");
		Long idLong=(long) 37145;
		SuspectInfo one=suspectInfoRepository.findBySId(idLong);	
		System.out.println("hehe!");
		return one;
	}
	

}
