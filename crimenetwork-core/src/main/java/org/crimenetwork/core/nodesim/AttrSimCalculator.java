package org.crimenetwork.core.nodesim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.crimenetwork.core.nodesim.type.AddressSimCalculator;
import org.crimenetwork.core.nodesim.type.DateSimCalculator;
import org.crimenetwork.core.nodesim.type.NumberSimCalculator;
import org.crimenetwork.core.nodesim.type.StringSimCalculator;
import org.crimenetwork.core.nodesim.type.TextSimCalculator;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;

public class AttrSimCalculator {
	
	private static List<String> counterfeitMoneyAttrNameList=null;
	private static List<String> caseAttrNameList=null;
	private static List<String> suspectAttrNameList=null;
	
	public static List<String> getCounterfeitMoneyAttrNameList(){
		if(counterfeitMoneyAttrNameList==null){
			counterfeitMoneyAttrNameList =new ArrayList<>();
			counterfeitMoneyAttrNameList.add("currencyType");
			counterfeitMoneyAttrNameList.add("versionType");
			counterfeitMoneyAttrNameList.add("categoryType");
			counterfeitMoneyAttrNameList.add("denomination");
			counterfeitMoneyAttrNameList.add("guanzihao");
			counterfeitMoneyAttrNameList.add("thick");
			counterfeitMoneyAttrNameList.add("quantity");
		}
		return counterfeitMoneyAttrNameList;
	}
	
	
	public static List<String> getCaseAttrNameList(){
		if(caseAttrNameList==null){
			caseAttrNameList =new ArrayList<>();
			caseAttrNameList.add("registerUnit");
			caseAttrNameList.add("registerDate");
			caseAttrNameList.add("seizedAmount");
			caseAttrNameList.add("briefInfo");
			caseAttrNameList.add("caseHappenDetailAddress");
			caseAttrNameList.add("caseHappenTime");
		}
		return caseAttrNameList;
	}
	
	public static List<String> getSuspectAttrNameList(){
		if(suspectAttrNameList==null){
			suspectAttrNameList =new ArrayList<>();
			suspectAttrNameList.add("nativeLocation");
			suspectAttrNameList.add("gender");
			suspectAttrNameList.add("accent");
			suspectAttrNameList.add("occupation");
			suspectAttrNameList.add("currentAddress");
			suspectAttrNameList.add("residenceAddress");
			suspectAttrNameList.add("nation");
			suspectAttrNameList.add("isLc");
			suspectAttrNameList.add("isReoffender");
			suspectAttrNameList.add("isHabitualOffender");
			suspectAttrNameList.add("caseInfo");
		}
		return suspectAttrNameList;
	}
	
	public static HashMap<String, Double> computeSimilar(CounterfeitMoney object1, CounterfeitMoney object2){
		HashMap<String, Double> simHashMap=new HashMap<String, Double>();
		simHashMap.put("currencyType", StringSimCalculator.calculate(object1.getCurrencyType(),object2.getCurrencyType()));
		simHashMap.put("versionType", StringSimCalculator.calculate(object1.getVersionType(),object2.getVersionType()));		
		simHashMap.put("categoryType", StringSimCalculator.calculate(object1.getCategoryType(),object2.getCategoryType()));
		simHashMap.put("denomination", StringSimCalculator.calculate(object1.getDenomination(),object2.getDenomination()));
		simHashMap.put("guanzihao", StringSimCalculator.calculate(object1.getGuanzihao(),object2.getGuanzihao()));
		
		if(object1.getCurrencyExtendInfo()!=null&&object2.getCurrencyExtendInfo()!=null){
			simHashMap.put("quantity", NumberSimCalculator.calculateCurrencyQuantity(object1.getCurrencyExtendInfo().getQuantity(),object2.getCurrencyExtendInfo().getQuantity()));
			simHashMap.put("thick",NumberSimCalculator.calculateCurrencyThick(object1.getCurrencyExtendInfo().getThick(),object2.getCurrencyExtendInfo().getThick()));
		}
		else{
			simHashMap.put("quantity", (double) 0);
			simHashMap.put("thick", (double) 0);
		}
		return simHashMap;
	}	
	
	public static HashMap<String, Double> computeSimilar(SuspectInfo object1,SuspectInfo object2){
		HashMap<String, Double> simHashMap=new HashMap<String, Double>();
		
		simHashMap.put("nativeLocation", StringSimCalculator.calculate(object1.getNativeLocation(),object2.getNativeLocation()));
		simHashMap.put("gender",StringSimCalculator.calculate(object1.getGender(),object2.getGender()));
		simHashMap.put("accent",StringSimCalculator.calculate(object1.getAccent(),object2.getAccent()));
		simHashMap.put("occupation",StringSimCalculator.calculate(object1.getOccupation(),object2.getOccupation()));
		
		simHashMap.put("currentAddress",AddressSimCalculator.calculate(object1.getLocationCodeByCurrentAddress(),object2.getLocationCodeByCurrentAddress(),object1.getAdress(),object2.getAdress()));
		simHashMap.put("residenceAddress",AddressSimCalculator.calculate(object1.getLocationCodeByRegisteredResidence(),object2.getLocationCodeByRegisteredResidence(),object1.getRegisteredResidenceDetail(),object2.getRegisteredResidenceDetail()));
		
		simHashMap.put("nation",StringSimCalculator.calculate(object1.getNation(),object2.getNation()));
		simHashMap.put("isLc",StringSimCalculator.calculate(object1.getIsLc(),object2.getIsLc()));
		simHashMap.put("isReoffender",StringSimCalculator.calculate(object1.getIsReoffender(),object2.getIsReoffender()));
		simHashMap.put("isHabitualOffender",StringSimCalculator.calculate(object1.getIsHabitualOffender(),object2.getIsHabitualOffender()));	
		
		simHashMap.put("caseInfo", TextSimCalculator.calculate(object1.getCaseInfo(), object2.getCaseInfo()));
		return simHashMap;
	}
	
	public static HashMap<String, Double> computeSimilar(CrimeCase object1,CrimeCase object2){
		HashMap<String, Double> simHashMap=new HashMap<String, Double>();
		simHashMap.put("registerUnit", StringSimCalculator.calculate(object1.getRegisterUnit(),object2.getRegisterUnit()));
		simHashMap.put("registerDate", DateSimCalculator.calculate(object1.getRegisterDate(),object2.getRegisterDate()));		
		simHashMap.put("seizedAmount", NumberSimCalculator.calculateSeizedAmount(object1.getSeizedAmount(),object2.getSeizedAmount()));
		simHashMap.put("caseHappenDetailAddress", AddressSimCalculator.calculate(object1.getCaseHappenLocation(),object2.getCaseHappenLocation(),object1.getCaseHappenDetailAddress(),object2.getCaseHappenDetailAddress()));
		simHashMap.put("caseHappenTime", DateSimCalculator.calculate(object1.getCaseHappenTime(),object2.getCaseHappenTime()));
		simHashMap.put("briefInfo", TextSimCalculator.calculate(object1.getBriefInfo(),object2.getBriefInfo()));
		return simHashMap;
	}	
	
}
