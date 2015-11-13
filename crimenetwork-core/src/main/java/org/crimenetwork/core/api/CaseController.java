package org.crimenetwork.core.api;

import org.crimenetwork.mongodb.entity.suspect.MSuspectBaseInfo;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.DBCursor;


@Controller
public class CaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(CaseController.class);
	
	@Autowired
	@Qualifier("suspectDao")
	private BasicRepository<MSuspectBaseInfo> suspectDao;

	@RequestMapping(value = "/case", method = RequestMethod.GET)
	@ResponseBody
	public MSuspectBaseInfo loadScriptContent() {
		logger.debug("here!");
		DBCursor cursor =suspectDao.findByAll();
		MSuspectBaseInfo one=(MSuspectBaseInfo) suspectDao.findOne(cursor);	
		return one;
	}

}
