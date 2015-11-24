package org.crimenetwork.dataextraction.service;

import org.crimenetwork.dataextraction.convert.instance.SuspectConverter;
import org.crimenetwork.mongodb.entity.suspect.MSuspectBaseInfo;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.repository.SuspectBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("suspectService")
public class SuspectService {
	@Autowired
	@Qualifier("suspectDao")
	BasicRepository<MSuspectBaseInfo> suspectDao;
	
	@Autowired
	@Qualifier("suspectBaseDao")
    private SuspectBaseDao suspectBaseDao;
	

    public void findAll(int page)
    {
    	long count = suspectBaseDao.count();
    	System.out.println(count);
    	Page<SuspectBaseInfo> readPage = suspectBaseDao.findAll(new PageRequest(page-1, 10));
    	SuspectConverter suspectConverter = new SuspectConverter();
    	for(SuspectBaseInfo cbi:readPage.getContent()){
    		MSuspectBaseInfo mCaseBaseInfo=(MSuspectBaseInfo) suspectConverter.convert(cbi);
    		try {
    			suspectDao.saveAndUpdate(mCaseBaseInfo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
    		
    	}
    		
    }
    
    public void moveALLData()
    {
    	System.out.println("Process suspect data start.");
    	int onepage=1000;
    	long count = suspectBaseDao.count();
    	SuspectConverter suspectConverter = new SuspectConverter();
    	for(int i=0;i<=count/onepage;i++){
    		Page<SuspectBaseInfo> readPage = suspectBaseDao.findAll(new PageRequest(i, onepage));
    		for(SuspectBaseInfo cbi:readPage.getContent()){
        		MSuspectBaseInfo mCaseBaseInfo=(MSuspectBaseInfo) suspectConverter.convert(cbi);
        		try {
        			suspectDao.saveAndUpdate(mCaseBaseInfo);
    			} catch (Exception e) {
    				// TODO: handle exception
    				e.printStackTrace();   				
    			}    		
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	System.out.println("Process suspect data end.");
    	System.out.println();
    }
}
