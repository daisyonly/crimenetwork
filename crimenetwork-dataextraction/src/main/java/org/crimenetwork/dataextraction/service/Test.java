package org.crimenetwork.dataextraction.service;

import java.util.LinkedList;
import java.util.List;

import org.crimenetwork.mongodb.entity.Case;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.crimenetwork.oracle.entity.CaseBaseInfo;
import org.crimenetwork.oracle.repository.CaseBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service("gotest")
public class Test {
	@Autowired
    private CaseBaseDao caseBaseDao;
	
	@Autowired
	@Qualifier("caseDao")
	BasicRepository<Case> caseDao;

    public void findAll(int page)
    {
    	Page<CaseBaseInfo> readPage = caseBaseDao.findAll(new PageRequest(page-1, 10));
    	process(readPage.getContent());
    	
    }
    
    private void process(List<CaseBaseInfo> list){
    	for(CaseBaseInfo cbi:list){
    		Case ca =new Case();
    		ca.setBriefInfo(cbi.getBriefInfo());
    		ca.setCaseId(cbi.getCaseId());
    		ca.setCaseName(cbi.getCaseName());
    		ca.setRegisterUnit(cbi.getRegisterUnit());
    		ca.setSeizedAmount(cbi.getSeizedAmount());
    		caseDao.saveAndUpdate(ca);
    	}

    }

}
