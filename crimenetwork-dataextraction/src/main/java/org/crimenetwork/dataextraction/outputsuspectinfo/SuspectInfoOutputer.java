package org.crimenetwork.dataextraction.outputsuspectinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.crimenetwork.dataextraction.utility.FileUtil;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectStatueCode;
import org.crimenetwork.oracle.repository.SuspectBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("suspectInfoOutputer")
public class SuspectInfoOutputer {
	
	@Autowired
	@Qualifier("suspectBaseDao")
    private SuspectBaseDao suspectBaseDao;
	

    public void run()
    {
    	FileUtil fileUtil=new FileUtil("C:\\Users\\Daisy\\Desktop\\suspectData.csv","out", false);
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
        		countAlia++;
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	
		
    	HashMap<String, List<SuspectBaseInfo>> infos=new HashMap<String, List<SuspectBaseInfo>>();
    	
    	for(int i=0;i<=count/onepage;i++){
    		Page<SuspectBaseInfo> readPage = suspectBaseDao.findAll(new PageRequest(i, onepage));
    		for(SuspectBaseInfo cbi:readPage.getContent()){
    			if(countName.containsKey(cbi.getName())
    					&&countName.get(cbi.getName())>4){
    				if(infos.containsKey(cbi.getName())){
    					infos.get(cbi.getName()).add(cbi);
    				}else{
    					List<SuspectBaseInfo> tmp=new ArrayList<SuspectBaseInfo>();
    					tmp.add(cbi);
    					infos.put(cbi.getName(), tmp);
    				}
    				
    			}
    			
    			if(cbi.getAlias()!=null){
    				String aliasKey=cbi.getAlias().trim();
    				if(countName.containsKey(aliasKey)
        					&&countName.get(aliasKey)>4){
        				if(infos.containsKey(aliasKey)){
        					infos.get(aliasKey).add(cbi);
        				}else{
        					List<SuspectBaseInfo> tmp=new ArrayList<SuspectBaseInfo>();
        					tmp.add(cbi);
        					infos.put(aliasKey, tmp);
        				}	
        			}
    			}
    		}
    	}
    	
   
    	@SuppressWarnings("unchecked")
		List<String> scount[]= new List[500];
    	for(int i=0;i<500;i++){
    		scount[i]=new ArrayList<String>();
    	}
    	for(Map.Entry<String, Integer> one:countName.entrySet()){
    		if(one.getValue()>4){
    			//System.out.println(one.getKey()+":"+one.getValue());
    			scount[one.getValue()].add(one.getKey());
    		}
    	}
    	
    	String title="id(数据库id),suspectId(嫌疑人id),name(姓名),alias(曾用名),agname(绰号),idCardNumber(身份证号码),nativeLocation(籍贯),"
    		+"gender(性别),phoneNumber(电话号码),nation(民族),caseInfo(涉案信息),accent(口音),locationCodeByCurrentAddress(现住地址编码),"
    		+"adress(现住详细地址),locationCodeByRegisteredResidence(户籍编码),registeredResidenceDetail(户籍详细地址),occupation(职业),"
    		+"isLc(是否流窜),isReoffender(是否累犯),isHabitualOffender(是否惯犯),qqNumber(qq号码),bankAccount(银行账号),"
    		+"handwritingNum(笔记采集编号),fingerprintNum(指纹采集编号),dnaNum(DNA采集编号),bodyType(体型),marriageState(婚姻情况),"
    		+"countryCode(国籍),bloodType(血型),faceType(脸型),certificateTypeByCertificate1Type(证件1类型),certificate1Number(证件1号码),"
    		+"certificate2Number(证件2号码),isForeigner(是否是境外人员),captureTime(抓获时间),criminalGroup(犯罪团伙),status(身份),"
    		+"specialstatus(特殊身份),height(身高)";
    	fileUtil.writeLine(title);
    	for(int i=499;i>=5;i--){
    		if(scount[i].size()!=0){
    			fileUtil.writeLine("重复次数："+i+" 共"+scount[i].size()+"个名字");			
    			for(String str:scount[i]){
    				List<SuspectBaseInfo> suspects=infos.get(str);
    				for(SuspectBaseInfo suspect:suspects){
    					fileUtil.writeLine(suspectToString(suspect));
    				}
    				fileUtil.newLine();
    			}
    			fileUtil.newLine();
    		}
    	}
    	fileUtil.close();
    	
    	System.out.println("countAlia:"+countAlia);
    
    	System.out.println("Process suspect data end.");
    	System.out.println();
    }
    private String suspectToString(SuspectBaseInfo suspect) {
        SuspectStatueCode codeMap=SuspectStatueCode.getAnInstance();
        if(suspect.getCaseInfo()!=null){
        	suspect.setCaseInfo(suspect.getCaseInfo().replaceAll("\r\n", " "));
        }
    	StringBuilder sb=new StringBuilder();
    	sb.append(suspect.getId()).append(",")
    	  .append(suspect.getSuspectId()).append(",")
    	  .append(suspect.getName()).append(",")
    	  .append(suspect.getAlias()).append(",")
    	  .append(suspect.getAgname()).append(",")
    	  .append(suspect.getIdCardNumber()).append(",")
    	  .append(suspect.getNativeLocation()).append(",")
    	  .append(suspect.getGender()).append(",")
    	  .append(suspect.getPhoneNumber()).append(",")
    	  .append(suspect.getNation()).append(",")
    	  .append("\"").append(suspect.getCaseInfo()).append("\"").append(",")
    	  .append(suspect.getAccent()).append(",")
    	  .append(suspect.getLocationCodeByCurrentAddress()).append(",")
    	  .append(suspect.getAdress()).append(",")
    	  .append(suspect.getLocationCodeByRegisteredResidence()).append(",")
    	  .append(suspect.getRegisteredResidenceDetail()).append(",")
    	  .append(suspect.getOccupation()).append(",")
    	  .append(suspect.getIsLc()).append(",")
    	  .append(suspect.getIsReoffender()).append(",")
    	  .append(suspect.getIsHabitualOffender()).append(",")
    	  .append(suspect.getQqNumber()).append(",")
    	  .append(suspect.getBankAccount()).append(",")
    	  .append(suspect.getHandwritingNum()).append(",")
    	  .append(suspect.getFingerprintNum()).append(",")
    	  .append(suspect.getDnaNum()).append(",")
    	  .append(suspect.getBodyType()).append(",")
    	  .append(suspect.getMarriageState()).append(",")
    	  .append(suspect.getCountryCode()).append(",")
    	  .append(suspect.getBloodType()).append(",")
    	  .append(suspect.getFaceType()).append(",")
    	  .append(suspect.getCertificateTypeByCertificate1Type()).append(",")
    	  .append(suspect.getCertificate1Number()).append(",")
    	  .append(suspect.getCertificate2Number()).append(",")
    	  .append(suspect.getIsForeigner()).append(",")
    	  .append(suspect.getCaptureTime()).append(",")
    	  .append(suspect.getCriminalGroup()).append(",")
    	  .append(codeMap.getStatusByCode(suspect.getStatus())).append(",")
    	  .append(codeMap.getSpecialstatusByCode(suspect.getSpecialstatus())).append(",")
    	  .append(suspect.getHeight());
    	  
    	return sb.toString();
    }
   
}
