package org.crimenetwork.dataextraction.service;


import org.crimenetwork.dataextraction.convert.instance.CaseConverter;
import org.crimenetwork.mongodb.entity.cases.MCaseBaseInfo;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.repository.CaseBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service("caseService")
public class CaseService {
	@Autowired
	@Qualifier("caseDao")
	BasicRepository<MCaseBaseInfo> caseDao;
	
	@Autowired
	@Qualifier("caseBaseDao")
    private CaseBaseDao caseBaseDao;
	

    public void findAll(int page)
    {
    	Page<CaseBaseInfo> readPage = caseBaseDao.findAll(new PageRequest(page-1, 10));
    	CaseConverter caseConverter = new CaseConverter();
    	for(CaseBaseInfo cbi:readPage.getContent()){
    		MCaseBaseInfo mCaseBaseInfo=(MCaseBaseInfo) caseConverter.convert(cbi);
    		try {
    			caseDao.saveAndUpdate(mCaseBaseInfo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
    		
    	}
    		
    }
    
    

}
