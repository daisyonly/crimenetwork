package org.crimenetwork.core.service;

import java.util.HashMap;

import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;

/**
 * config the attributes that need to transfer to the user end.
 * @author Daisy
 *
 */
public class EntityToMapConverter {
	
	public static HashMap<String, String> convert(SuspectInfo suspectInfo){
		HashMap<String, String> res=new HashMap<String, String>();
		res.put("嫌疑人编号", suspectInfo.getSuspectId());
		res.put("身份证", suspectInfo.getIdCardNumber());
		res.put("姓名", suspectInfo.getName());
		res.put("籍贯", suspectInfo.getNativeLocation());
		res.put("No", suspectInfo.getsId().toString());
		return res;
	}
	
	
	public static HashMap<String, String> convert(CrimeCase crimeCase){
		HashMap<String, String> res=new HashMap<String, String>();
		res.put("案件名称", crimeCase.getCaseName());
		res.put("案件编号", crimeCase.getCaseId());
		res.put("案发地点", crimeCase.getCaseHappenDetailAddress());
		if(crimeCase.getCaseHappenTime()!=null)
			res.put("案发时间", crimeCase.getCaseHappenTime().toString());
		else res.put("案发时间", "暂无");
		res.put("No", crimeCase.getcId().toString());
		return res;
	}
	
	public static HashMap<String, String> convert(CounterfeitMoney counterfeitMoney){
		HashMap<String, String> res=new HashMap<String, String>();
		res.put("票样编号", counterfeitMoney.getPiaoyangNumber());
		res.put("类别", counterfeitMoney.getCurrencyType());
		res.put("年版", counterfeitMoney.getVersionType());
		res.put("冠字号", counterfeitMoney.getGuanzihao());
		res.put("No", counterfeitMoney.getFmid().toString());
		return res;
	}

}
