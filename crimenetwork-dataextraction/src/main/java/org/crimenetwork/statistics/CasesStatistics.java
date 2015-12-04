package org.crimenetwork.statistics;

import java.util.HashMap;
import java.util.HashSet;

import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.repository.CaseBaseDao;
import org.crimenetwork.oracle.repository.JiabiBaseDao;
import org.crimenetwork.oracle.repository.SuspectBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("casesStatistics")
public class CasesStatistics {

	@Autowired
	@Qualifier("jiabiBaseDao")
    private JiabiBaseDao jiabiBaseDao;
	
	@Autowired
	@Qualifier("caseBaseDao")
    private CaseBaseDao caseBaseDao;
	
	@Autowired
	@Qualifier("suspectBaseDao")
    private SuspectBaseDao suspectBaseDao;
	
	
	HashSet<Long> jiabiIdSet=new HashSet<Long>();
	HashSet<Long> caseIdSet=new HashSet<Long>();
	HashSet<Long> suspectIdSet=new HashSet<Long>();
	HashMap<String, HashSet<String>> edges=new HashMap<String, HashSet<String>>(); 
   
    public void run()
    {   	
    	System.out.println("Process cases data start.");
    	int onepage=1000;
    	long count = caseBaseDao.count();
    	for(int i=0;i<=count/onepage;i++){
    		Page<CaseBaseInfo> readPage = caseBaseDao.findAll(new PageRequest(i, onepage));
    		for(CaseBaseInfo cbi:readPage.getContent()){ 			
    			findCase(cbi);
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	System.out.println("Process cases data end.");
    	MapHelper.write(edges, "C:\\Users\\Daisy\\Desktop\\mapC.txt");
    }
    
    
    private void findCase(CaseBaseInfo cbi){
    	String start="C"+cbi.getId();
    	for(JiabiBaseInfo jbi:cbi.getCounterfeitMoneys()){
    		
    		String end="J"+jbi.getFmid();
    		if(edges.containsKey(start)){
    			if(edges.get(start).contains(end)) continue;
    			else{
    				edges.get(start).add(end);
    				findJiabi(jbi);
    			}
    		}else{
    			HashSet<String> tmp=new HashSet<String>();
    			tmp.add(end);
    			edges.put(start, tmp);
    			findJiabi(jbi);
    		}
    	}
    	for(SuspectBaseInfo sbi:cbi.getSuspects()){
    		
    		String end="S"+sbi.getId();
    		if(edges.containsKey(start)){
    			if(edges.get(start).contains(end)) continue;
    			else{
    				edges.get(start).add(end);
    				findSuspect(sbi);
    			}
    		}else{
    			HashSet<String> tmp=new HashSet<String>();
    			tmp.add(end);
    			edges.put(start, tmp);
    			findSuspect(sbi);
    		}
    		
    	}
    }
    
    private void findJiabi(JiabiBaseInfo jbi){
    	String start="J"+jbi.getFmid();
    	for(CaseBaseInfo cbi:jbi.getCaseInfos()){
    	
    		String end="C"+cbi.getId();
    		if(edges.containsKey(start)){
    			if(edges.get(start).contains(end)) continue;
    			else{
    				edges.get(start).add(end);
    				findCase(cbi);
    			}
    		}else{
    			HashSet<String> tmp=new HashSet<String>();
    			tmp.add(end);
    			edges.put(start, tmp);
    			findCase(cbi);
    		}	
    	}
    }
    
    
    private void findSuspect(SuspectBaseInfo sbi){
    	String start="S"+sbi.getId();
    	for(CaseBaseInfo cbi:sbi.getCases()){
    		//if(cbi.getId().equals(father.getId())) continue;
    		String end="C"+cbi.getId();
    		if(edges.containsKey(start)){
    			if(edges.get(start).contains(end)) continue;
    			else{
    				edges.get(start).add(end);
    				findCase(cbi);
    			}
    		}else{
    			HashSet<String> tmp=new HashSet<String>();
    			tmp.add(end);
    			edges.put(start, tmp);
    			findCase(cbi);
    		}
    	}
    }
    

}
