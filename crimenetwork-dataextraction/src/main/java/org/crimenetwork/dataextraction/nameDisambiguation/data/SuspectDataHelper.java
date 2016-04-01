package org.crimenetwork.dataextraction.nameDisambiguation.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.crimenetwork.dataextraction.utility.FileUtil;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.repository.SuspectBaseDao;
import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.internalDocBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("suspectDataHelper")
public class SuspectDataHelper {
	
	private ArrayList<ArrayList<SuspectBaseInfo>> data;
	public int count;
	
	private List<List<Set<Long>>> labelList;
	
	@Autowired
	@Qualifier("suspectBaseDao")
    private SuspectBaseDao suspectBaseDao;
	
	public void init(){
		labelList=new ArrayList<List<Set<Long>>>();
		if(data==null) loadDataFromDataBase();
	}
	
	
	public List<Set<Long>> getLabelList(int index) {
		return labelList.get(index);
	}


	public void initEva(){
		labelList=new ArrayList<List<Set<Long>>>();
		if(data==null) loadData();
	}
	public ArrayList<SuspectBaseInfo> getDataByGroupIndex(int index){
		if(index>=count) return null;
		return data.get(index);
	}
	private void loadDataFromDataBase(){
		System.out.println("Process suspect data start.");
    	int onepage=1000;
    	long suspectCount = suspectBaseDao.count();
    	HashMap<String, Integer> countName=new HashMap<String, Integer>();
    	
    	HashSet<String> countId=new HashSet<String>();
    	for(int i=0;i<=suspectCount/onepage;i++){
    		Page<SuspectBaseInfo> readPage = suspectBaseDao.findAll(new PageRequest(i, onepage));
    		for(SuspectBaseInfo cbi:readPage.getContent()){
        		if(cbi.getSuspectId()!=null){
        			if(countId.contains(cbi.getSuspectId())){
            			continue;
            		} 
            		countId.add(cbi.getSuspectId());
        		}
        		if(cbi.getName()!=null){
        			if(cbi.getName().equals("未知")) continue;
        			if(countName.containsKey(cbi.getName())){
           			 countName.put(cbi.getName(), countName.get(cbi.getName())+1);
        			}else{
           			 	countName.put(cbi.getName(), 1);
        			} 		
        		}
    			
        		if(cbi.getAlias()!=null) {
        			String key=cbi.getAlias().trim();
        			if(key.endsWith("")) continue;
        			if(key.equals("无")) continue;
        			if(key.equals("－－－")) continue;
        			if(key.equals("（无）")) continue;
        			if(key.equals("(无)")) continue;
        			if(key.equals("未知")) continue;
        			if(key.equals("??")) continue;
        			if(key.equals("//")) continue;
        			if(key.equals("/")) continue;
        			if(key.equals("-")) continue;
        			if(key.equals("0")) continue;
        			if(key.equals("1")) continue;
        			if(key.equals("11")) continue;
        			
        			if(countName.containsKey(key)){
           			 	countName.put(key, countName.get(key)+1);
        			}else{
           			 	countName.put(key, 1);
        			} 	 
        		}
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	
		
    	HashMap<String, ArrayList<SuspectBaseInfo>> infos=new HashMap<String, ArrayList<SuspectBaseInfo>>();
    	
    	for(int i=0;i<=suspectCount/onepage;i++){
    		Page<SuspectBaseInfo> readPage = suspectBaseDao.findAll(new PageRequest(i, onepage));
    		for(SuspectBaseInfo cbi:readPage.getContent()){
    			if(countName.containsKey(cbi.getName())
    					&&countName.get(cbi.getName())>1){
    				if(infos.containsKey(cbi.getName())){
    					infos.get(cbi.getName()).add(cbi);
    				}else{
    					ArrayList<SuspectBaseInfo> tmp=new ArrayList<SuspectBaseInfo>();
    					tmp.add(cbi);
    					infos.put(cbi.getName(), tmp);
    				}
    				
    			}
    			
    			if(cbi.getAlias()!=null){
    				String aliasKey=cbi.getAlias().trim();
    				if(countName.containsKey(aliasKey)
        					&&countName.get(aliasKey)>1){
        				if(infos.containsKey(aliasKey)){
        					infos.get(aliasKey).add(cbi);
        				}else{
        					ArrayList<SuspectBaseInfo> tmp=new ArrayList<SuspectBaseInfo>();
        					tmp.add(cbi);
        					infos.put(aliasKey, tmp);
        				}	
        			}
    			}
    		}
    	}
    	data =new ArrayList<ArrayList<SuspectBaseInfo>>();
    	count=0;
    	for(Map.Entry<String, ArrayList<SuspectBaseInfo>> one:infos.entrySet()){
    		if(one.getKey().equals("why?")||one.getKey().equals("无")) continue;
    		data.add(one.getValue());
    		if(one.getValue().size()>30) System.out.println(one.getKey());
    		++count;
    	}
    	System.out.println("why?");
   
	}
	
	private void loadData() {
		FileUtil fileUtil=new FileUtil("C:\\Users\\Daisy\\Desktop\\suspectData.csv","in", false);
		String line;
		data =new ArrayList<ArrayList<SuspectBaseInfo>>();
		ArrayList<SuspectBaseInfo> tmp=new ArrayList<SuspectBaseInfo>();
		HashMap<Integer, Set<Long>> labelHashMap=new HashMap<Integer, Set<Long>>();
		line=fileUtil.readLine();
		line=fileUtil.readLine();
		String lastLine="start";
		count=0;
		while((line=fileUtil.readLine())!=null){		
			if(line.startsWith("重复次数")||line.equals("")){
				lastLine=line;
				continue;
			}
			
			if((lastLine.startsWith("重复次数")||lastLine.equals(""))&&
					!line.equals("")&&line.charAt(0)>='0'&&line.charAt(0)<='9'){
				data.add(tmp);
				List<Set<Long>> tmpList =new ArrayList<Set<Long>>();
				for(Map.Entry<Integer, Set<Long>> entry:labelHashMap.entrySet()){
					tmpList.add(entry.getValue());
				}
				labelList.add(tmpList);
				tmp=new ArrayList<SuspectBaseInfo>();
				labelHashMap=new HashMap<Integer, Set<Long>>();
				++count;
				lastLine=line;
			}
			
			String[] strs=line.split(",");
			int flag=0;
			try {
				flag=Integer.parseInt(strs[strs.length-1]);
			} catch (Exception e) {
				//System.out.println(line);
			}
			Long id=Long.parseLong(strs[0]);
			SuspectBaseInfo sbi=suspectBaseDao.findById(id);
			tmp.add(sbi);
			if(labelHashMap.containsKey(flag)){
				labelHashMap.get(flag).add(id);
			}else{
				Set<Long> tmpSet =new HashSet<Long>();
				tmpSet.add(id);
				labelHashMap.put(flag, tmpSet);
			}
			//System.out.println(line);
			lastLine=line;		
		}
		data.add(tmp);
		List<Set<Long>> ttmpList =new ArrayList<Set<Long>>();
		for(Map.Entry<Integer, Set<Long>> entry:labelHashMap.entrySet()){
			ttmpList.add(entry.getValue());
		}
		labelList.add(ttmpList);
		++count;	
	}
	public static void main(String[] args) {
		String[] springConfig  = 
			{	"classpath:dataextraction/applicationContext.xml", 
			};
		
		@SuppressWarnings("resource")
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
			
		SuspectDataHelper suspectDataHelper = (SuspectDataHelper) context.getBean("suspectDataHelper");
		suspectDataHelper.initEva();
		
        System.out.println( "Hello World!" );
	}
	


}
