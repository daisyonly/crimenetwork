package org.crimenetwork.dataextraction.service;

import org.crimenetwork.dataextraction.convert.instance.JiabiConverter;
import org.crimenetwork.mongodb.entity.currency.MJiabiBaseInfo;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.repository.JiabiBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("jiabiService")
public class JiabiService {
	@Autowired
	@Qualifier("jiabiDao")
	BasicRepository<MJiabiBaseInfo> jiabiDao;
	
	@Autowired
	@Qualifier("jiabiBaseDao")
    private JiabiBaseDao jiabiBaseDao;
	

    public void findAll(int page)
    {
    	Page<JiabiBaseInfo> readPage = jiabiBaseDao.findAll(new PageRequest(page-1, 10));
    	JiabiConverter jiabiConverter = new JiabiConverter();
    	for(JiabiBaseInfo cbi:readPage.getContent()){
    		MJiabiBaseInfo mJiabiBaseInfo=(MJiabiBaseInfo) jiabiConverter.convert(cbi);
    		try {
    			jiabiDao.saveAndUpdate(mJiabiBaseInfo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();				
			} 		
    	}    		
    }
    
    public void moveALLData()
    {
    	System.out.println("Process jiabi data start.");
    	int onepage=1000;
    	long count = jiabiBaseDao.count();
    	JiabiConverter jiabiConverter = new JiabiConverter();
    	for(int i=0;i<=count/onepage;i++){
    		Page<JiabiBaseInfo> readPage = jiabiBaseDao.findAll(new PageRequest(i, onepage));
    		for(JiabiBaseInfo cbi:readPage.getContent()){
    			MJiabiBaseInfo mJiabiBaseInfo=(MJiabiBaseInfo) jiabiConverter.convert(cbi);
        		try {
        			jiabiDao.saveAndUpdate(mJiabiBaseInfo);
    			} catch (Exception e) {
    				// TODO: handle exception
    				e.printStackTrace();
    				
    			}    		
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	System.out.println("Process jiabi data end.");
    	System.out.println();
    }
    
    

}
