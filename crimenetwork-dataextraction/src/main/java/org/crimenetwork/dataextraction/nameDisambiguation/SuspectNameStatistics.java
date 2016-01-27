package org.crimenetwork.dataextraction.nameDisambiguation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.crimenetwork.dataextraction.utility.FileUtil;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.repository.SuspectBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("suspectNameStatistics")
public class SuspectNameStatistics {
	
	@Autowired
	@Qualifier("suspectBaseDao")
    private SuspectBaseDao suspectBaseDao;
	

    public void run()
    {
    	FileUtil fileUtil=new FileUtil("C:\\Users\\Daisy\\Desktop\\name3.txt","out", false);
    	System.out.println("Process suspect data start.");
    	int onepage=1000;
    	long count = suspectBaseDao.count();
    	HashMap<String, Integer> countName=new HashMap<String, Integer>();
    	int countAlia=0;
    	HashSet<String> countId=new HashSet<String>();
    	for(int i=0;i<=count/onepage;i++){
    		Page<SuspectBaseInfo> readPage = suspectBaseDao.findAll(new PageRequest(i, onepage));
    		for(SuspectBaseInfo cbi:readPage.getContent()){
        		if(cbi.getSuspectId()!=null){
        			if(countId.contains(cbi.getSuspectId())){
            			continue;
            		} 
            		countId.add(cbi.getSuspectId());
        		}
    			if(countName.containsKey(cbi.getName())){
        			 countName.put(cbi.getName(), countName.get(cbi.getName())+1);
        		 }else{
        			 countName.put(cbi.getName(), 1);
        		 } 
        		 if(cbi.getAlias()!=null) countAlia++;
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	@SuppressWarnings("unchecked")
		List<String> scount[]= new List[500];
    	for(int i=0;i<500;i++){
    		scount[i]=new ArrayList<String>();
    	}
    	for(Map.Entry<String, Integer> one:countName.entrySet()){
    		if(one.getValue()>1){
    			//System.out.println(one.getKey()+":"+one.getValue());
    			scount[one.getValue()].add(one.getKey());
    		}
    	}
    	
    	for(int i=499;i>=2;i--){
    		if(scount[i].size()!=0){
    			fileUtil.writeLine("重复次数："+i+" 共"+scount[i].size()+"人");
    			
    			for(String str:scount[i]){
    				fileUtil.write(str+", ");
    			}
    			fileUtil.newLine();
    		}
    	}
    	fileUtil.close();
    	System.out.println("countAlia:"+countAlia);
    
    	System.out.println("Process suspect data end.");
    	System.out.println();
    }
    
   
}
