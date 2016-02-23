package org.crimenetwork.dataextraction.nameDisambiguation.attribute;

import java.util.ArrayList;

import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectStatueCode;

public class SimilarCalculator {
	
	private static SuspectStatueCode codeMap=SuspectStatueCode.getAnInstance();
	
	public static double computeClusterSimilar(ArrayList<SuspectBaseInfo> cluster1,
			ArrayList<SuspectBaseInfo> cluster2){
		double sim=Double.MIN_VALUE;
	    for(SuspectBaseInfo one:cluster1){
	    	for(SuspectBaseInfo two:cluster2){
	    		double cur=computeSimilar(one,two);
	    		if(cur>sim) sim=cur;
	    	}
	    }
		return sim;
	} 
	
	public static double computeSimilar(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2){
		double sum=(compareImportanceAttribute(sbi1, sbi2)*3+
				     compareAddressAttribute(sbi1, sbi2)*3+
				     compareNormalAttribute(sbi1, sbi2))*1.0;
		return sum/37;
	}
	
	/**
	 * 7个属性
	 * agname绰号，alias曾用名，nativeLocation籍贯，gender性别,accent口音
	 * marriagestate婚姻情况,occupation职业
	 * @param sbi1
	 * @param sbi2
	 * @return
	 */	
	private static int compareImportanceAttribute(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2) {
		int sum=0;
		sum+=compare(sbi1.getAgname(),sbi2.getAgname());//绰号
		
		if(sbi1.getGender()!=null&&sbi2.getGender()!=null)
			sum+=compare(sbi1.getGender().getName(),sbi2.getGender().getName());//曾用名
		
		if(sbi1.getNativeLocation()!=null&&sbi2.getNativeLocation()!=null)
			sum+=compare(sbi1.getNativeLocation().getName(),sbi2.getNativeLocation().getName());
		sum+=compare(sbi1.getAccent().getName(),sbi2.getAccent().getName());
		
		if(sbi1.getName().endsWith(sbi2.getName())) 
			sum+=compare(sbi1.getAlias(),sbi2.getAlias());
		
		if(sbi1.getMarriageState()!=null&&sbi2.getMarriageState()!=null)
			sum+=compare(sbi1.getMarriageState().getName(),sbi2.getMarriageState().getName());
		
		if(sbi1.getOccupation()!=null&&sbi2.getOccupation()!=null)
			sum+=compare(sbi1.getOccupation().getName(),sbi2.getOccupation().getName());
		
		return sum;
	}
	
	/**
	 * 2个属性
	 * 比较现住地址和户籍地址两个属性
	 * @param sbi1
	 * @param sbi2
	 * @return
	 */
	private static int compareAddressAttribute(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2) {
		int sum=0;
		String code1=sbi1.getLocationCodeByCurrentAddress();
		String code2=sbi2.getLocationCodeByCurrentAddress();
		if(code1.equals("999999")||code2.equals("999999")){
			String ad1=sbi1.getAdress();
			String ad2=sbi2.getAdress();
			if(ad1==null||ad2==null) sum=sum+0;
			else{
				ad1=ad1.trim();
				ad2=ad2.trim();
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
				ad1=ad1.trim();
				ad2=ad2.trim();
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
	
	/**
	 * 10个属性
	 * nation民族，isLc是否流窜，isReoffender是否累犯，isHabitualOffender是否惯犯
	 * bodyType体型，bloodType血型，faceType脸型，height身高,status身份，specialstatus特殊身份
	 * @param sbi1
	 * @param sbi2
	 * @return
	 */
	private static int compareNormalAttribute(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2) {
		int sum=0;
		
		if(sbi1.getNation()!=null&&sbi2.getNation()!=null)
			sum+=compare(sbi1.getNation().getName(),sbi2.getNation().getName());
		
		if(sbi1.getIsLc()!=null&&sbi2.getIsLc()!=null)
			sum+=compare(sbi1.getIsLc().getName(),sbi2.getIsLc().getName());
		
		if(sbi1.getIsReoffender()!=null&&sbi2.getIsReoffender()!=null)
			sum+=compare(sbi1.getIsReoffender().getName(),sbi2.getIsReoffender().getName());
		
		if(sbi1.getIsHabitualOffender()!=null&&sbi2.getIsHabitualOffender()!=null)
			sum+=compare(sbi1.getIsHabitualOffender().getName(),sbi2.getIsHabitualOffender().getName());
		
		if(sbi1.getBodyType()!=null&&sbi2.getBodyType()!=null)
			sum+=compare(sbi1.getBodyType().getName(),sbi2.getBodyType().getName());
		
		if(sbi1.getBloodType()!=null&&sbi2.getBloodType()!=null)
			sum+=compare(sbi1.getBloodType().getName(),sbi2.getBloodType().getName());
		
		if(sbi1.getFaceType()!=null&&sbi2.getFaceType()!=null)
			sum+=compare(sbi1.getFaceType().getName(),sbi2.getFaceType().getName());
		
		sum+=processHeight(sbi1.getHeight(),sbi1.getHeight());
		
		String status1=codeMap.getStatusByCode(sbi1.getStatus());
		String status2=codeMap.getStatusByCode(sbi2.getStatus());
		sum+=compare(status1,status2);
		
		String specialstatus1=codeMap.getSpecialstatusByCode(sbi1.getSpecialstatus());
		String specialstatus2=codeMap.getSpecialstatusByCode(sbi2.getSpecialstatus());
		sum+=compare(specialstatus1,specialstatus2);
		return sum;
	}
	
	private static int processHeight(String height1,String height2){
		String[] str1s=height1.trim().split("-");
		String[] str2s=height1.trim().split("-");
		if(str1s.length==0||str2s.length==0) return 0;
		if(str1s.length>2||str2s.length>2) return 0;
		if(str1s.length==2&&str2s.length==2){
			int num11=Integer.parseInt(str1s[0]);
			int num12=Integer.parseInt(str1s[1]);
			
			int num21=Integer.parseInt(str2s[0]);
			int num22=Integer.parseInt(str2s[1]);
			
			if((num11<=num21)&&(num22<=num12)||
					(num11>=num21)&&(num22>=num12)) 
					return 1;
			else return -1;
		}
		
		if(str1s.length==1&&str2s.length==2){
			int num11=Integer.parseInt(str1s[0]);
			
			int num21=Integer.parseInt(str2s[0]);
			int num22=Integer.parseInt(str2s[1]);
			
			if((num21<=num11)&&(num11<=num22)) return 1;
			else return -1;
		}
		
		if(str1s.length==2&&str2s.length==1){
			int num11=Integer.parseInt(str1s[0]);
			int num12=Integer.parseInt(str1s[1]);
			
			int num21=Integer.parseInt(str2s[0]);
			
			if((num11<=num21)&&(num21<=num12))return 1;
			else return -1;
		}	
		return 0;
	}
	
	
	

	private static int compare(String str1,String str2){
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
