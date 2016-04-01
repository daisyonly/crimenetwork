package org.crimenetwork.dataextraction.nameDisambiguation.attribute;


import java.util.List;

import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;

public class StrongRulesProcessor {
	
	/**
	 * idCardNumber身份证号，phoneNumber电话号码，qqNumber QQ号码， bankAccout银行账户
	 * 其他证件
	 * @param cluster
	 * @param sbi
	 * @return
	 */
	public static boolean isTheSameOne(List<SuspectBaseInfo> cluster,SuspectBaseInfo sbi){
		for(SuspectBaseInfo one:cluster){
			if(compareOne(one,sbi)){
				return true;		
			}
		}
		return false;
	}
	
	private static boolean compareOne(SuspectBaseInfo one,SuspectBaseInfo sbi){
		//由于保护隐私，身份证号暂时没有
		//if(compare(one.getIdCardNumber(),sbi.getIdCardNumber())) return true;
		if(compare(one.getPhoneNumber(),sbi.getPhoneNumber())) return true;
		if(compare(one.getQqNumber(),sbi.getQqNumber())) return true;
		if(compare(one.getBankAccount(),sbi.getBankAccount())) return true;
		if(!isUnknow(one.getCertificateTypeByCertificate1Type().getName())
				&&!isUnknow(sbi.getCertificateTypeByCertificate1Type().getName())){
			if(compare(one.getCertificate1Number(),sbi.getCertificate1Number())) return true;
			if(compare(one.getCertificate2Number(),sbi.getCertificate2Number())) return true;
		}
		return false;
	}
	
	private static boolean compare(String str1,String str2){
		if(isUnknow(str1)||isUnknow(str2)) return false;
		str1=str1.trim();
		str2=str2.trim();
		if(str1.equals(str2)) return true;	
		return false;
	}

	private static boolean isUnknow(String str){
		if(str==null)  return true;
		if(str.trim().equals("未知")) return true;
		if(str.trim().equals("无")) return true;
		return false;
	}

}
