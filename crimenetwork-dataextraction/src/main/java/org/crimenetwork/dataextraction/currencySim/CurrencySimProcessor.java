package org.crimenetwork.dataextraction.currencySim;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Service("currencySimProcessor")
public class CurrencySimProcessor {
	
	final Logger logger = LoggerFactory.getLogger(CurrencySimProcessor.class);
	
	@Autowired
	private CounterfeitMoneyRepository counterfeitMoneyRepository;
	
	@Autowired
	@Qualifier("jiabiBaseDao")
    private JiabiBaseDao jiabiBaseDao;
	
	
    public void run()
    {
    	System.out.println("Process jiabi data start.");
    	int onepage=1000;
    	long count = jiabiBaseDao.count();
    	HashMap<String, List<Long>> guanzihaoMap=new HashMap<String, List<Long>>();
    	for(int i=0;i<=count/onepage;i++){
    		Page<JiabiBaseInfo> readPage = jiabiBaseDao.findAll(new PageRequest(i, onepage));
    		for(JiabiBaseInfo cbi:readPage.getContent()){
    			 if(guanzihaoMap.containsKey(cbi.getGuanzihao())){
    				 guanzihaoMap.get(cbi.getGuanzihao()).add(cbi.getFmid());
    			 }else{
    				 List<Long> tmp=new ArrayList<Long>();
    				 tmp.add(cbi.getFmid());
    				 guanzihaoMap.put(cbi.getGuanzihao(), tmp);
    			 }		
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	System.out.println("Process jiabi data end.");
    	System.out.println();
    	int guanziCount=0;
    	for(Map.Entry<String, List<Long>> one:guanzihaoMap.entrySet()){
    		if(one.getValue().size()>1){
    			guanziCount++;
    		}
    	}
    	System.out.println("guanzihao repetition:"+guanziCount);
    	
    }
    
    

}
