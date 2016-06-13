package org.crimenetwork.core.api;


import org.crimenetwork.core.entity.NetworkModel;
import org.crimenetwork.core.service.NodeSimService;
import org.crimenetwork.core.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/*
 * core class
 * the controller of searching with rank.
 * when a network has too many nodes, it will be very slow
 * ps: I think searching without rank is more useful when add in an real-time system.
 */
@Controller
public class SearchController{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private NodeSimService nodeSimService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public NetworkModel loadScriptContent(@RequestParam("suspectId") String suspectId,
			@RequestParam("caseId") String caseId,
			@RequestParam("currencyId") String currencyId,
			@RequestParam(value="pathLength", required=false) int pathLength) {
		
		String id=null;
		if(!suspectId.equals("")) id="S"+suspectId;
		else if(!caseId.equals("")) id="C"+caseId;
		else if(!currencyId.equals("")) id="J"+currencyId;
		System.out.println(id);
		NetworkModel res=searchService.searchByObjectId(id, pathLength+1);
		res.queryId=id;
		nodeSimService.rank(res);//calculate the similarity
		return res;
	}

}
