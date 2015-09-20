package org.crimenetwork.dataextraction.service;

import java.util.List;

import org.crimenetwork.mongodb.entity.Case;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.cases.CaseInfo;
import org.crimenetwork.oracle.repository.CaseBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service("gotest")
public class Test {
	@Autowired
	@Qualifier("caseDao")
	BasicRepository<Case> caseDao;
	
	@Autowired
	@Qualifier("caseBaseDao")
    private CaseBaseDao caseBaseDao;
	

    public void findAll(int page)
    {
    	Page<CaseInfo> readPage = caseBaseDao.findAll(new PageRequest(page-1, 10));
    	process(readPage.getContent());   	
    }
    
    private void process(List<CaseInfo> list){
    	for(CaseInfo cbi:list){
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
