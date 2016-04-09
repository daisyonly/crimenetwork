package org.crimenetwork.dataextraction.currencySim;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.crimenetwork.dataextraction.utility.FileUtil;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.relationship.CurrencySim;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.repository.JiabiBaseDao;
import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.internalDocBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    	Long skipOne=(long) 14047;
    	Long skipTwo=(long) 16035;
    	for(Map.Entry<String, List<Long>> entry:guanzihaoMap.entrySet()){
    		if(entry.getValue().contains(skipOne)||
    				entry.getValue().contains(skipTwo)) {
    			continue;
    		}
    		if(entry.getValue().size()>1) updateOneGuanzihao(entry.getValue());
    		
    	}	 	
    }
    
    
    public void count()
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
    	Map<Integer, Integer> countMap=new HashMap<Integer, Integer>();
    	for(Map.Entry<String, List<Long>> entry:guanzihaoMap.entrySet()){
    		int listCount=entry.getValue().size();
    		if(listCount>10) System.out.println(":"+entry.getKey());
    		if(countMap.containsKey(listCount)){
    			countMap.put(listCount, countMap.get(listCount)+1);
    		}else{
    			countMap.put(listCount, 1);
    		}    		
    	}
    	for(Entry<Integer, Integer> entry:countMap.entrySet()){
    		System.out.println(entry.getKey()+"\t"+entry.getValue());
    	}
    }
    
    
    private void updateOneGuanzihao(List<Long> idList){
    	List<CounterfeitMoney> counterfeitMoneyList=new ArrayList<CounterfeitMoney>();
	    int originalListSize=idList.size();
    	for(Long id:idList){
	    	CounterfeitMoney cm = counterfeitMoneyRepository.findByFmid(id);
	    	if(cm==null){
	    		originalListSize--;
	    		if(originalListSize==1) return;
	    		else continue;
	    	}
	    	counterfeitMoneyList.add(cm);
	    }
	    for(int i=0;i<counterfeitMoneyList.size();i++){
	    	for(int j=0;j<counterfeitMoneyList.size();j++){
	    		if(i==j) continue;
	    		CurrencySim sim=new CurrencySim(counterfeitMoneyList.get(i),counterfeitMoneyList.get(j),true);
	    		if(checkCurrencySim(sim,counterfeitMoneyList.get(i).getSimilarCM()))
	    			counterfeitMoneyList.get(i).getSimilarCM().add(sim);		
	    	}
	    }
	    System.out.println("id list:");
	    for(CounterfeitMoney cm:counterfeitMoneyList){
	    	System.out.print(cm.getFmid()+" ");
	    }
	    System.out.println();
	    counterfeitMoneyRepository.save(counterfeitMoneyList);
    }
    
    private boolean checkCurrencySim(CurrencySim sim, Set<CurrencySim> similarCM) {
		for(CurrencySim cs:similarCM){
			if(cs.getFrom().getFmid().equals(sim.getFrom().getFmid())&&
					cs.getTo().getFmid().equals(sim.getTo().getFmid()))
					return false;
		}
		return true;
	}

	public void output(HashMap<String, List<Long>> guanzihaoMap){
    	FileUtil fileUtil=new FileUtil("C:\\Users\\Daisy\\Desktop\\guanzihao.txt","out", false);
    	int guanziCount=0;
    	for(Map.Entry<String, List<Long>> one:guanzihaoMap.entrySet()){
    		if(one.getValue().size()>1){
    			guanziCount++;
    			for(Long id:one.getValue()){
    				fileUtil.write(id+" ");
    			}
    			fileUtil.writeLine("");
    			
    		}
    	}
    	fileUtil.writeLine("guanzihao repetition:" + guanziCount);
    	fileUtil.close();
    }
	public static void main(String[] args) {
		Set<Long> testLongs=new HashSet<Long>();
		Long one=(long) 123;
		testLongs.add(one);
		Long two=(long) 123;
		if(testLongs.contains(two)) System.out.println("haha");
	}
    
    

}
