package org.crimenetwork.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.crimenetwork.oracle.entity.currency.DenominationType;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBasePic;
import org.crimenetwork.oracle.repository.DenominationTypeDao;
import org.crimenetwork.oracle.repository.JiabiBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("jiabiSimService")
public class JiabiSimService {
	@Autowired
	@Qualifier("jiabiBaseDao")
    private JiabiBaseDao jiabiBaseDao;
	
	@Autowired
	@Qualifier("denominationTypeDao")
	private DenominationTypeDao denominationTypeDao;
	
	private Dalian dalian = new Dalian();
	private HashMap<String, HashMap<Integer, Double>> result;
	
	public void runAll(String dataPath){
		System.out.println("Process jiabi data start.");
    	int onepage=1000;
    	long count = jiabiBaseDao.count();
    	for(int i=0;i<=count/onepage;i++){
    		Page<JiabiBaseInfo> readPage = jiabiBaseDao.findAll(new PageRequest(i, onepage));
    		for(JiabiBaseInfo jbi:readPage.getContent()){
    			HashMap<String, HashMap<Integer, Double>> ans=getSim(jbi.getFmid());
    			MapHelper.write("J"+jbi.getFmid(),ans, dataPath);
    		}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	System.out.println("Process jiabi data end.");
	}
	
	public HashMap<String, HashMap<Integer, Double>> getSim(Long id){
		result=new HashMap<String, HashMap<Integer, Double>>();
		JiabiBaseInfo jbi=jiabiBaseDao.findByFmid(id);
		String valueModuleType = getValueModuleType(jbi.getDenominationType().getValue_moduleType());
		DenominationType insDenominationType= denominationTypeDao.findByCode(jbi.getDenominationType().getCode());
		Set<JiabiBaseInfo> candidateJiabi= insDenominationType.getJiabiBaseInfos();
		
		if(checkPicType(jbi.getJiabiBasePics(),0)){
			List<String> jiabiIDs=getJiabiIDbyPicType(candidateJiabi,0);
			if(jiabiIDs.size()!=0) calculateSim(jbi.getFmid(),jiabiIDs,0,valueModuleType);
		}
		
		if(checkPicType(jbi.getJiabiBasePics(),1)){
			List<String> jiabiIDs=getJiabiIDbyPicType(candidateJiabi,1);
			if(jiabiIDs.size()!=0) calculateSim(jbi.getFmid(),jiabiIDs,1,valueModuleType);
		}
		
		if(checkPicType(jbi.getJiabiBasePics(),2)){
			List<String> jiabiIDs=getJiabiIDbyPicType(candidateJiabi,2);
			if(jiabiIDs.size()!=0) calculateSim(jbi.getFmid(),jiabiIDs,2,valueModuleType);
		}
		
		return result;
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
	
	private List<String> getJiabiIDbyPicType(Set<JiabiBaseInfo> candidateJiabi,int type) {
		List<String> list=new ArrayList<String>();
		if(type==0){
			for(JiabiBaseInfo jbi:candidateJiabi){
				boolean baiZheng=false,baiFang=false;
				for(JiabiBasePic jbc:jbi.getJiabiBasePics()){
					if(jbc.getPicType().getPcid()==1) baiZheng=true;
					if(jbc.getPicType().getPcid()==2) baiFang=true;
				}
				if(baiZheng&&baiFang)list.add(jbi.getFmid().toString());
			}
		}else if(type==1){
			for(JiabiBaseInfo jbi:candidateJiabi){
				boolean ziZheng=false;
				for(JiabiBasePic jbc:jbi.getJiabiBasePics()){
					if(jbc.getPicType().getPcid()==3) ziZheng=true;
				}
				if(ziZheng)list.add(jbi.getFmid().toString());
			}
		}else if(type==2){
			for(JiabiBaseInfo jbi:candidateJiabi){
				boolean ziFang=false;
				for(JiabiBasePic jbc:jbi.getJiabiBasePics()){
					if(jbc.getPicType().getPcid()==4) ziFang=true;
				}
				if(ziFang)list.add(jbi.getFmid().toString());
			}
		}
		return list;
	}

	public String getValueModuleType(String valueModuleType){	
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
				System.out.println("current pro:"+pro);
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
			double simValue=Double.parseDouble(target[i+1])*4;
			if(simValue<=0.0){//相似度为负数的置为0
				simValue=0;
			}
			if(simValue>=100.0){//相似度大于100的置为99
				simValue=99;
			}
			updateResult(target[i],compareType,simValue);
		}
		long t2=System.currentTimeMillis();
		System.out.println("比对运行时间："+(t2-t1));	
	}
	
	private void updateResult(String fmid,int compareType,double simValue){
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
