package org.crimenetwork.dataextraction.nodeSim;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.crimenetwork.dataextraction.utility.FileUtil;
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

@Service("suspectCounter")
public class SuspectCounter {
	
	final Logger logger = LoggerFactory.getLogger(SuspectCounter.class);
	
	@Autowired
	private SuspectInfoRepository suspectInfoRepository;
	
	@Autowired
	@Qualifier("suspectBaseDao")
    private SuspectBaseDao suspectBaseDao;
	
	@Autowired
	private SearchService searchService;

    
    public void countALLData()
    {
    	FileUtil fileUtil=new FileUtil("D:\\毕业设计\\svmrank\\data\\simRankData.txt","out", false);
    	System.out.println("Process suspect data start.");
    	int onepage=1000;
    	long count = suspectBaseDao.count();
    	Set<String> set=new HashSet<String>();
        Map<Integer, Integer> countCircleMap=new HashMap<Integer, Integer>();
    	for(int i=0;i<=count/onepage;i++){
    		Page<SuspectBaseInfo> readPage = suspectBaseDao.findAll(new PageRequest(i, onepage));
    		for(SuspectBaseInfo cbi:readPage.getContent()){
    			
    			if(set.contains("S"+cbi.getId())) continue;
    			
    			SuspectInfo suspectInfo= suspectInfoRepository.findBySId(cbi.getId());
    			if(suspectInfo==null) continue;
    			try {
    				NetworkModel res=searchService.searchBySuspectInfo(suspectInfo, 100);
    				Set<String> keysList=res.nodes.keySet();
    				if(countCircleMap.containsKey(keysList.size())){
    					countCircleMap.put(keysList.size(), countCircleMap.get(keysList.size())+1);
    				}else{
    					countCircleMap.put(keysList.size(), 1);
    				}
    				for(String id:keysList){
						if(!set.contains(id)){
							set.add(id);
						}
					}
    				if(keysList.size()>10){
    				    System.out.println(suspectInfo.getsId()+":"+keysList.size());
    					for(String id:keysList){
    						fileUtil.write(id+",");
    					}
    					fileUtil.writeLine("");
    				}			
    			} catch (Exception e) {
    				// TODO: handle exception
    				logger.error("suspect id "+cbi.getId()+":",e);   				
    			}    		
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	for(Map.Entry<Integer, Integer> entry: countCircleMap.entrySet()){
    		System.out.println(entry.getKey()+" : "+entry.getValue());
    	}
    	System.out.println("Process suspect data end.");
    	System.out.println();
    	fileUtil.close();
    }
}
