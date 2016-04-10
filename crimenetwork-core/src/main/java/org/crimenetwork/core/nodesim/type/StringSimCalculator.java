package org.crimenetwork.core.nodesim.type;

public class StringSimCalculator {
	
	public static double calculate(String str1,String str2){
		if(isUnknow(str1)||isUnknow(str2)) return 0;
		str1=str1.trim();
		str2=str2.trim();
		if(str1.equals(str2)) return 5;	
		return 0;
	}

	private static boolean isUnknow(String str){
		if(str==null)  return true;
		if(str.trim().equals("未知")) return true;
		return false;
	}
	

}
