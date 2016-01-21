package org.crimenetwork.modeling.service;


import org.crimenetwork.modeling.convert.instance.CurrencyConverter;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.repository.JiabiBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("currencyService")
public class CurrencyService {
	
	final Logger logger = LoggerFactory.getLogger(CurrencyService.class);
	
	@Autowired
	private CounterfeitMoneyRepository counterfeitMoneyRepository;
	
	@Autowired
	@Qualifier("jiabiBaseDao")
    private JiabiBaseDao jiabiBaseDao;
	
	@Autowired
    private CurrencyConverter jiabiConverter;
	
    public void moveALLData()
    {
    	System.out.println("Process jiabi data start.");
    	int onepage=1000;
    	long count = jiabiBaseDao.count();
    	for(int i=0;i<=count/onepage;i++){
    		Page<JiabiBaseInfo> readPage = jiabiBaseDao.findAll(new PageRequest(i, onepage));
    		for(JiabiBaseInfo cbi:readPage.getContent()){
    			CounterfeitMoney mJiabiBaseInfo= jiabiConverter.setAndConvert(cbi, true);
        		try {
        			mJiabiBaseInfo.deleteRepeatingData();
        			counterfeitMoneyRepository.save(mJiabiBaseInfo);
    			} catch (Exception e) {
    				// TODO: handle exception
    				logger.error("currency id "+mJiabiBaseInfo.getFmid()+":",e);	
    			}    		
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	System.out.println("Process jiabi data end.");
    	System.out.println();
    }
    
    

}
