package org.crimenetwork.currencysim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.crimenetwork.oracle.entity.currency.JiabiBasePic;
import org.crimenetwork.oracle.entity.jiabisim.SimDenominationType;
import org.crimenetwork.oracle.entity.jiabisim.SimJiabiBaseInfo;
import org.crimenetwork.oracle.repository.SimDenominationTypeDao;
import org.crimenetwork.oracle.repository.SimJiabiBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jiabiSimService")
@Transactional
public class JiabiSimService {
	@Autowired
	@Qualifier("simJiabiBaseDao")
    private SimJiabiBaseDao jiabiBaseDao;
	
	@Autowired
	@Qualifier("simDenominationTypeDao")
	private SimDenominationTypeDao simDenominationTypeDao;
	
	private Dalian dalian;
	private HashMap<String, HashMap<Integer, Double>> result;
	
	
	final Logger logger = LoggerFactory.getLogger(JiabiSimService.class);
	
	
	
	public void runAll(String dataPath,String logPath){
		FileUtil fileData=new FileUtil(dataPath, "out",false);
		FileUtil fileLog=new FileUtil(logPath, "out",false);
		dalian = new Dalian(fileLog);
		System.out.println("Process jiabi data start.");
    	int onepage=1000;	
    	
    	long count = jiabiBaseDao.count();
    	for(int i=0;i<=count/onepage;i++){
    		long t1=System.currentTimeMillis();
    		Page<SimJiabiBaseInfo> readPage = jiabiBaseDao.findAll(new PageRequest(i, onepage));
    		long t2=System.currentTimeMillis();
    		System.out.println("找1000个的时间："+(t2-t1));	
    		for(SimJiabiBaseInfo jbi:readPage.getContent()){
    			HashMap<String, HashMap<Integer, Double>> ans=getSim(jbi.getFmid());
    			if(ans!=null&&ans.size()!=0)
    				MapHelper.write("J"+jbi.getFmid(),ans, fileData);
    		}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	   
    	}
    	
    	fileData.close();
    	fileLog.close();
    	System.out.println("Process jiabi data end.");
	}
	
	
	public HashMap<String, HashMap<Integer, Double>> getSim(Long fmid){
		result=new HashMap<String, HashMap<Integer, Double>>();
		SimJiabiBaseInfo jbi=jiabiBaseDao.findByFmid(fmid);
		//只和年版类型相同的假币进行比较
		String valueModuleType = getValueModuleType(jbi.getDenominationType().getValue_moduleType());
		if(valueModuleType==null) return null;
		long t1=System.currentTimeMillis();
		SimDenominationType insDenominationType= simDenominationTypeDao.findByCode(jbi.getDenominationType().getCode());
		long t2=System.currentTimeMillis();
		System.out.println("找年版类型相同的假币的时间："+(t2-t1));
		Set<SimJiabiBaseInfo> candidateJiabi= insDenominationType.getJiabiBaseInfos();
		
		for(SimJiabiBaseInfo aa:candidateJiabi){
			if(aa.getJiabiBasePics().size()!=0) break;
		}
		//白光正面
		if(checkPicType(jbi.getJiabiBasePics(),0)){
			List<String> jiabiIDs=getJiabiIDbyPicType(candidateJiabi,0);
			if(jiabiIDs.size()!=0) calculateSim(jbi.getFmid(),jiabiIDs,0,valueModuleType);
		}
		
		//紫光正面
		if(checkPicType(jbi.getJiabiBasePics(),1)){
			List<String> jiabiIDs=getJiabiIDbyPicType(candidateJiabi,1);
			if(jiabiIDs.size()!=0) calculateSim(jbi.getFmid(),jiabiIDs,1,valueModuleType);
		}
		
		//紫光反面
		if(checkPicType(jbi.getJiabiBasePics(),2)){
			List<String> jiabiIDs=getJiabiIDbyPicType(candidateJiabi,2);
			if(jiabiIDs.size()!=0) calculateSim(jbi.getFmid(),jiabiIDs,2,valueModuleType);
		}	
		
		return result;
	}
	
	public String getValueModuleType(String valueModuleType){	
		if(valueModuleType==null) return null;
		if(valueModuleType.equals("[Hundred05]")){//分类参数
			valueModuleType="Hundred05";
		}else if(valueModuleType.equals("[Fifty05]")){
			valueModuleType="Fifty05";
		}else if(valueModuleType.equals("[Twenty05]")){
			valueModuleType="Twenty05";
		}else if(valueModuleType.equals("[Ten05]")){
			valueModuleType="Ten05";
		}
		else if(valueModuleType.equals("[Five05]")){
			valueModuleType="Five05";
		}else if(valueModuleType.equals("[Hundred99]")){
			valueModuleType="Hundred99";
		}
		else if(valueModuleType.equals("[Fifty99]")){
			valueModuleType="Fifty99";
		}else{
			
		}
		return valueModuleType;
	}
	
	
	private boolean checkPicType(Set<JiabiBasePic> pics, int type) {
		
		if (type == 0) {
			boolean baiZheng = false, baiFang = false;
			for (JiabiBasePic jbc : pics) {
				if (jbc.getPicType().getPcid() == 1)
					baiZheng = true;
				if (jbc.getPicType().getPcid() == 2)
					baiFang = true;
			}
			return baiZheng && baiFang;

		} else if (type == 1) {

			boolean ziZheng = false;
			for (JiabiBasePic jbc : pics) {
				if (jbc.getPicType().getPcid() == 3)
					ziZheng = true;
			}
			return ziZheng;

		} else if (type == 2) {

			boolean ziFang = false;
			for (JiabiBasePic jbc : pics) {
				if (jbc.getPicType().getPcid() == 4)
					ziFang = true;
			}
			return ziFang;

		}
		return false;
	}
	
	private List<String> getJiabiIDbyPicType(Set<SimJiabiBaseInfo> candidateJiabi,int type) {
		List<String> list=new ArrayList<String>();
		if(type==0){
			for(SimJiabiBaseInfo jbi:candidateJiabi){
				boolean baiZheng=false,baiFang=false;
				for(JiabiBasePic jbc:jbi.getJiabiBasePics()){
					if(jbc.getPicType().getPcid()==1) baiZheng=true;
					if(jbc.getPicType().getPcid()==2) baiFang=true;
				}
				if(baiZheng&&baiFang)list.add(jbi.getFmid().toString());
			}
		}else if(type==1){
			for(SimJiabiBaseInfo jbi:candidateJiabi){
				boolean ziZheng=false;
				for(JiabiBasePic jbc:jbi.getJiabiBasePics()){
					if(jbc.getPicType().getPcid()==3) ziZheng=true;
				}
				if(ziZheng)list.add(jbi.getFmid().toString());
			}
		}else if(type==2){
			for(SimJiabiBaseInfo jbi:candidateJiabi){
				boolean ziFang=false;
				for(JiabiBasePic jbc:jbi.getJiabiBasePics()){
					if(jbc.getPicType().getPcid()==4) ziFang=true;
				}
				if(ziFang)list.add(jbi.getFmid().toString());
			}
		}
		return list;
	}

	
	private void calculateSim(Long id, List<String> jiabis, int compareType, String valueModuleType){
		long t1=System.currentTimeMillis();
		String type;
		int queueType;
		if(compareType==1){//zi zheng
			type=Dalian.TYPE_PURPLE;
			queueType=Dalian.QUEUE_TYPE_ZHENG;
		}else if(compareType==2){//zi fan
			type=Dalian.TYPE_PURPLE;
			queueType=Dalian.QUEUE_TYPE_fan;
		}else{
			type=Dalian.TYPE_WHITE;
			queueType=Dalian.QUEUE_TYPE_ALL;
		}
		StringBuilder targetList=new StringBuilder("");
		
		for(int i=0;i<jiabis.size();i++){
			targetList.append(jiabis.get(i));
			if(i<jiabis.size()-1)targetList.append(",");
		}	
		
		String taskId = dalian.NewQueueTask(id, targetList.toString(), type, valueModuleType,queueType);
		try{
			int pro = dalian.GetTaskProgress(taskId);
			while(pro!=100 && pro!=-1){
				//System.out.println("current pro:"+pro);
				//Thread.sleep(1000);
				pro = dalian.GetTaskProgress(taskId);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		String result = dalian.GetQueueList(taskId);
		System.out.println("xiangsidu result:"+result);
		String[] target = result.split(",");
		for(int i=0;i<target.length && i+1<target.length;i+=2){
			Map<String,Object> ele = new HashMap<String,Object>();
			ele.put("fmid", target[i]);//选中的票样fmid
			double simValue;
			try {
				simValue=Double.parseDouble(target[i+1]);
			} catch (NumberFormatException e) {
				logger.error("出现非数字情况:"+id+","+target[i]+","+target[i+1]);
				continue;
			}
			if(queueType==Dalian.QUEUE_TYPE_ALL){
				simValue=simValue*4;
			}else{
				simValue=simValue*100;
			}
			if(simValue<=0.0){//相似度为负数的置为0
				simValue=0;
			}
			if(simValue>=100.0){//相似度大于100的置为99
				simValue=100;
			}
			updateResult(target[i],compareType,simValue);
		}
		long t2=System.currentTimeMillis();
		System.out.println("比对运行时间："+(t2-t1));	
	}
	
	private void updateResult(String fmid,int compareType,double simValue){
		if(simValue==0) return;
		String key="J"+fmid;
		if(result.containsKey(key)){
			result.get(key).put(compareType, simValue);
		}else{
			HashMap<Integer, Double> tmp=new HashMap<Integer, Double>();
			tmp.put(compareType, simValue);
			result.put(key, tmp);
		}
	}
	
}
