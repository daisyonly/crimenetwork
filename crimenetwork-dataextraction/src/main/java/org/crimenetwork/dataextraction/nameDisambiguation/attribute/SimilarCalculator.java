package org.crimenetwork.dataextraction.nameDisambiguation.attribute;

import java.util.ArrayList;

import org.crimenetwork.dataextraction.nameDisambiguation.clusterService.ClusterProcessor;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectStatueCode;
import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.internalDocBuilder;

public class SimilarCalculator {
	
	private static SuspectStatueCode codeMap=SuspectStatueCode.getAnInstance();
	
	private static int norCount=0;
	private static int addCount=0;
	private static int impCount=0;
	public static double computeClusterSimilar(ArrayList<SuspectBaseInfo> cluster1,
			ArrayList<SuspectBaseInfo> cluster2){
		double sim=ClusterProcessor.MIN;;
	    for(SuspectBaseInfo one:cluster1){
	    	for(SuspectBaseInfo two:cluster2){
	    		double cur=computeSimilar(one,two);
	    		if(cur>sim) sim=cur;
	    	}
	    }
		return sim;
	} 
	
	public static double computeSimilar(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2){
		double impscore=compareImportanceAttribute(sbi1, sbi2)*3;
		double addscore=compareAddressAttribute(sbi1, sbi2)*5;
		double norscore=compareNormalAttribute(sbi1, sbi2);
		double sum=impscore+addscore+norscore;
		int total=norCount+addCount*5+impCount*3;
		if(addscore==0&&impscore==0) total*=2; 
		return sum/total;
	}
	
	/**
	 * 5个属性
	 * agname绰号，alias曾用名，accent口音，occupation职业
	 * @param sbi1
	 * @param sbi2
	 * @return
	 */	
	private static int compareImportanceAttribute(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2) {
		int sum=0;
		int zeroCount=0;
		
		int tmp=compare(sbi1.getAgname(),sbi2.getAgname());//绰号
		if(tmp==0) zeroCount++;
		else sum+=tmp;
		

		if(sbi1.getAccent()!=null&&sbi2.getAccent()!=null){
			tmp=compare(sbi1.getAccent().getName(),sbi2.getAccent().getName());
		    if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		if(sbi1.getName().equals(sbi2.getName())){ 
			tmp=compare(sbi1.getAlias(),sbi2.getAlias());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		if(sbi1.getOccupation()!=null&&sbi2.getOccupation()!=null){
			tmp=compare(sbi1.getOccupation().getName(),sbi2.getOccupation().getName());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		impCount=4-zeroCount;
		return sum;
	}
	
	/**
	 * 3个属性
	 * 比较现住地址和户籍地址两个属性 nativeLocation籍贯，
	 * @param sbi1
	 * @param sbi2
	 * @return
	 */
	private static int compareAddressAttribute(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2) {
		int sum=0;
		int zeroCount=0;
		String ad1=sbi1.getAdress();
		String ad2=sbi2.getAdress();
		String code1=sbi1.getLocationCodeByCurrentAddress();
		String code2=sbi2.getLocationCodeByCurrentAddress();
		int tmp= AddressSimCalculator.calculate2(code1,code2,ad1, ad2);
		if(tmp==0) zeroCount++;
		else sum+=tmp;
		
		if(sbi1.getNativeLocation()!=null&&sbi2.getNativeLocation()!=null){
			tmp=compare(sbi1.getNativeLocation().getName(),sbi2.getNativeLocation().getName());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		ad1=sbi1.getRegisteredResidenceDetail();
		ad2=sbi2.getRegisteredResidenceDetail();
		code1=sbi1.getLocationCodeByRegisteredResidence();
		code2=sbi2.getLocationCodeByRegisteredResidence();
		tmp= AddressSimCalculator.calculate2(code1,code2,ad1, ad2);
		if(tmp==0) zeroCount++;
		else sum+=tmp;
		addCount=3-zeroCount;
		return sum;
	}
	
	/**
	 * 12个属性
	 * nation民族，isLc是否流窜，isReoffender是否累犯，isHabitualOffender是否惯犯
	 * bodyType体型，bloodType血型，faceType脸型，height身高,status身份，specialstatus特殊身份
	 * gender性别，MarriageState婚姻状况
	 * @param sbi1
	 * @param sbi2
	 * @return
	 */
	private static int compareNormalAttribute(SuspectBaseInfo sbi1,SuspectBaseInfo sbi2) {
		int sum=0;
		int zeroCount=0;
		if(sbi1.getGender()!=null&&sbi2.getGender()!=null){
			int tmp=compare(sbi1.getGender().getName(),sbi2.getGender().getName());//曾用名
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		if(sbi1.getMarriageState()!=null&&sbi2.getMarriageState()!=null){
			int tmp=compare(sbi1.getMarriageState().getName(),sbi2.getMarriageState().getName());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		if(sbi1.getNation()!=null&&sbi2.getNation()!=null){
			int tmp=compare(sbi1.getNation().getName(),sbi2.getNation().getName());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		if(sbi1.getIsLc()!=null&&sbi2.getIsLc()!=null){
			int tmp=compare(sbi1.getIsLc().getName(),sbi2.getIsLc().getName());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		if(sbi1.getIsReoffender()!=null&&sbi2.getIsReoffender()!=null){
			int tmp=compare(sbi1.getIsReoffender().getName(),sbi2.getIsReoffender().getName());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		if(sbi1.getIsHabitualOffender()!=null&&sbi2.getIsHabitualOffender()!=null){
			int tmp=compare(sbi1.getIsHabitualOffender().getName(),sbi2.getIsHabitualOffender().getName());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		if(sbi1.getBodyType()!=null&&sbi2.getBodyType()!=null){
			int tmp=compare(sbi1.getBodyType().getName(),sbi2.getBodyType().getName());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		if(sbi1.getBloodType()!=null&&sbi2.getBloodType()!=null){
			int tmp=compare(sbi1.getBloodType().getName(),sbi2.getBloodType().getName());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		if(sbi1.getFaceType()!=null&&sbi2.getFaceType()!=null){
			int tmp=compare(sbi1.getFaceType().getName(),sbi2.getFaceType().getName());
			if(tmp==0) zeroCount++;
			else sum+=tmp;
		}else{
			zeroCount++;
		}
		
		int ttmp=0;
		ttmp=processHeight(sbi1.getHeight(),sbi1.getHeight());
		if(ttmp==0) zeroCount++;
		else sum+=ttmp;
		
		String status1=codeMap.getStatusByCode(sbi1.getStatus());
		String status2=codeMap.getStatusByCode(sbi2.getStatus());
		ttmp=compare(status1,status2);
		if(ttmp==0) zeroCount++;
		else sum+=ttmp;
		
		String specialstatus1=codeMap.getSpecialstatusByCode(sbi1.getSpecialstatus());
		String specialstatus2=codeMap.getSpecialstatusByCode(sbi2.getSpecialstatus());
		ttmp=compare(specialstatus1,specialstatus2);
		if(ttmp==0) zeroCount++;
		else sum+=ttmp;
		norCount=12-zeroCount;
		return sum;
	}
	
	private static int processHeight(String height1,String height2){
		if(isUnknow(height1)||isUnknow(height2)) return 0;
		String[] str1s=height1.trim().split("-");
		String[] str2s=height1.trim().split("-");
		if(str1s.length==0||str2s.length==0) return 0;
		if(str1s.length>2||str2s.length>2) return 0;
		if(str1s.length==2&&str2s.length==2){
			try{
				int num11=Integer.parseInt(str1s[0]);
				int num12=Integer.parseInt(str1s[1]);
				
				int num21=Integer.parseInt(str2s[0]);
				int num22=Integer.parseInt(str2s[1]);
				
				if((num11<=num21)&&(num22<=num12)||
						(num11>=num21)&&(num22>=num12)) 
						return 1;
				else return -1;
			}catch(Exception e){
				return 0;
			}
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
		if(str.trim().equals("无")) return true;
		return false;
	}
	
}
