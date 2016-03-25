package org.crimenetwork.core.nodesim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.crimenetwork.core.utility.LCSHelper;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;


public class CrimeCaseAttrSimCalculator {
	
	private List<String> attrNameList=null;
	
	public List<String> getAttrNameList(){
		if(attrNameList==null){
			attrNameList =new ArrayList<>();
			attrNameList.add("seizedAmount");
			attrNameList.add("briefInfo");
			attrNameList.add("caseHappenTime");
			attrNameList.add("occupation");
			attrNameList.add("currentAddress");
			attrNameList.add("residenceAddress");
			attrNameList.add("nation");
			attrNameList.add("isLc");
			attrNameList.add("isReoffender");
			attrNameList.add("isHabitualOffender");
		}
		return attrNameList;
	}
	
	public static HashMap<String, Double> computeSimilar(CrimeCase sbi1,SuspectInfo sbi2){
		HashMap<String, Double> simHashMap=new HashMap<String, Double>();
		
		simHashMap.put("nativeLocation", compare(sbi1.getNativeLocation(),sbi2.getNativeLocation()));
		simHashMap.put("gender",compare(sbi1.getGender(),sbi2.getGender()));
		simHashMap.put("accent",compare(sbi1.getAccent(),sbi2.getAccent()));
		simHashMap.put("occupation",compare(sbi1.getOccupation(),sbi2.getOccupation()));
		
		simHashMap.put("currentAddress",compareCurrentAddress(sbi1,sbi2));
		simHashMap.put("residenceAddress",compareResidenceAddress(sbi1,sbi2));
		
		simHashMap.put("nation",compare(sbi1.getNation(),sbi2.getNation()));
		simHashMap.put("isLc",compare(sbi1.getIsLc(),sbi2.getIsLc()));
		simHashMap.put("isReoffender",compare(sbi1.getIsReoffender(),sbi2.getIsReoffender()));
		simHashMap.put("isHabitualOffender",compare(sbi1.getIsHabitualOffender(),sbi2.getIsHabitualOffender()));	
		return simHashMap;
	}
	
	
	
	private static double compareResidenceAddress(SuspectInfo sbi1,SuspectInfo sbi2) {
		
		String code1=sbi1.getLocationCodeByRegisteredResidence();
		String code2=sbi2.getLocationCodeByRegisteredResidence();
		if(code1.equals("999999")||code2.equals("999999")){
			String ad1=sbi1.getRegisteredResidenceDetail();
			String ad2=sbi2.getRegisteredResidenceDetail();
			if(ad1==null||ad2==null) return 0;
			else{
				ad1=ad1.trim();
				ad2=ad2.trim();
				int lcscount=LCSHelper.LCS(ad1, ad2);
				int maxLength=Math.max(ad1.length(), ad2.length());
				if(lcscount*2>=maxLength) return 1;
				else return -1;
			}
		}else{
			if(code1.equals(code2)) return 1;
			else return -1;
		}
	}
	
	
	private static double compareCurrentAddress(SuspectInfo sbi1,SuspectInfo sbi2) {
		String code1=sbi1.getLocationCodeByCurrentAddress();
		String code2=sbi2.getLocationCodeByCurrentAddress();
		if(code1.equals("999999")||code2.equals("999999")){
			String ad1=sbi1.getAdress();
			String ad2=sbi2.getAdress();
			if(ad1==null||ad2==null) return 0;
			else{
				ad1=ad1.trim();
				ad2=ad2.trim();
				int lcscount=LCSHelper.LCS(ad1, ad2);
				int maxLength=Math.max(ad1.length(), ad2.length());
				if(lcscount*2>=maxLength) return 1;
				else return -1;
			}
		}else{
			if(code1.equals(code2)) return 1;
			else return -1;
		}
	}
	

	private static double compare(String str1,String str2){
		if(isUnknow(str1)||isUnknow(str2)) return 0;
		str1=str1.trim();
		str2=str2.trim();
		if(str1.equals(str2)) return 1;	
		return -1;
	}

	private static boolean isUnknow(String str){
		if(str==null)  return true;
		if(str.trim().equals("未知")) return true;
		return false;
	}
	
}
