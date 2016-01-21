package org.crimenetwork.modeling.service;

import org.crimenetwork.modeling.convert.instance.SuspectConverter;
import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.repository.SuspectInfoRepository;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.repository.SuspectBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("suspectService")
public class SuspectService {
	
	final Logger logger = LoggerFactory.getLogger(SuspectService.class);
	
	@Autowired
	private SuspectInfoRepository suspectInfoRepository;
	
	@Autowired
	@Qualifier("suspectBaseDao")
    private SuspectBaseDao suspectBaseDao;
	
	@Autowired
	private SuspectConverter suspectConverter;

    
    public void moveALLData()
    {
    	System.out.println("Process suspect data start.");
    	int onepage=1000;
    	long count = suspectBaseDao.count();

    	for(int i=0;i<=count/onepage;i++){
    		Page<SuspectBaseInfo> readPage = suspectBaseDao.findAll(new PageRequest(i, onepage));
    		for(SuspectBaseInfo cbi:readPage.getContent()){
    			SuspectInfo suspectInfo= suspectConverter.setAndConvert(cbi, true);
        		try {
        			suspectInfo.deleteRepeatingData();
        			suspectInfoRepository.save(suspectInfo);
    			} catch (Exception e) {
    				// TODO: handle exception
    				logger.error("suspect id "+suspectInfo.getsId()+":",e);   				
    			}    		
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	System.out.println("Process suspect data end.");
    	System.out.println();
    }
}
