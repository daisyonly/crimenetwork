package org.crimenetwork.dataextraction.nameDisambiguation.attribute;

import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectStatueCode;

public class ExactlySameComparator {
	
	private static SuspectStatueCode codeMap=SuspectStatueCode.getAnInstance();
	
	/**
	 * agname绰号，alias曾用名，nativeLocation籍贯，gender性别,accent口音
	 * status身份，specialstatus特殊身份
	 * @param sbi1
	 * @param sbi2
	 * @return
	 */	
	public static int compareImportanceAttribute(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2) {
		int sum=0;
		sum+=compare(sbi1.getAgname(),sbi2.getAgname());//绰号
		sum+=compare(sbi1.getGender().getName(),sbi2.getGender().getName());//曾用名
		sum+=compare(sbi1.getNativeLocation().getName(),sbi2.getNativeLocation().getName());
		sum+=compare(sbi1.getAccent().getName(),sbi2.getAccent().getName());
		
		if(sbi1.getName().endsWith(sbi2.getName())) 
			sum+=compare(sbi1.getAlias(),sbi2.getAlias());
		
		String status1=codeMap.getStatusByCode(sbi1.getStatus());
		String status2=codeMap.getStatusByCode(sbi2.getStatus());
		sum+=compare(status1,status2);
		
		String specialstatus1=codeMap.getSpecialstatusByCode(sbi1.getSpecialstatus());
		String specialstatus2=codeMap.getSpecialstatusByCode(sbi2.getSpecialstatus());
		sum+=compare(specialstatus1,specialstatus2);
		
		return sum;
	}
	
	/**
	 * 比较现住地址和户籍地址两个属性
	 * @param sbi1
	 * @param sbi2
	 * @return
	 */
	public static int compareAddressAttribute(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2) {
		int sum=0;
		String code1=sbi1.getLocationCodeByCurrentAddress();
		String code2=sbi2.getLocationCodeByCurrentAddress();
		if(code1.equals("999999")||code2.equals("999999")){
			String ad1=sbi1.getAdress();
			String ad2=sbi2.getAdress();
			if(ad1==null||ad2==null) sum=sum+0;
			else{
				int lcscount=LCSHelper.LCS(ad1, ad2);
				int maxLength=Math.max(ad1.length(), ad2.length());
				if(lcscount*2>=maxLength) sum=sum+1;
				else sum=sum-1;
			}
		}else{
			if(code1.equals(code2)) sum=sum+1;
			else sum=sum-1;
		}
		
		
		code1=sbi1.getLocationCodeByRegisteredResidence();
		code2=sbi2.getLocationCodeByRegisteredResidence();
		if(code1.equals("999999")||code2.equals("999999")){
			String ad1=sbi1.getRegisteredResidenceDetail();
			String ad2=sbi2.getRegisteredResidenceDetail();
			if(ad1==null||ad2==null) sum=sum+0;
			else{
				int lcscount=LCSHelper.LCS(ad1, ad2);
				int maxLength=Math.max(ad1.length(), ad2.length());
				if(lcscount*2>=maxLength) sum=sum+1;
				else sum=sum-1;
			}
		}else{
			if(code1.equals(code2)) sum=sum+1;
			else sum=sum-1;
		}
		
		return sum;
	}
	
	public static int compareNormalAttribute(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2) {
		int sum=0;
		//TODO
		
		return sum;
	}
	
	
	

	public static int compare(String str1,String str2){
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
	public static void main(String[] args) {
		String str="170";
		String[] strs=str.split("-");
		System.out.println("");
		
	}
	
}
