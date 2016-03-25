package org.crimenetwork.core.nodesim.type;

import org.crimenetwork.core.utility.LCSHelper;

public class AddressSimCalculator {
	
	public static double calculate(String address1,String address2){
		if(address1==null||address2==null) return 0;	
		address1=address1.trim();
		address2=address2.trim();
		int lcscount=LCSHelper.LCS(address1, address2);
		int maxLength=Math.max(address1.length(), address2.length());
		if(lcscount*2>=maxLength) return 1;
		else return -1;	
	}
	
    public static double calculate(String address1code,String address2code,String address1,String address2) {	
		
		if(address1code==null||address2code==null||
				address1code.equals("999999")||address2code.equals("999999")){
			
			if(address1==null||address2==null) return 0;
			else{
				address1=address1.trim();
				address2=address2.trim();
				int lcscount=LCSHelper.LCS(address1, address2);
				int maxLength=Math.max(address1.length(), address2.length());
				if(lcscount*2>=maxLength) return 1;
				else return -1;
			}
		}else{
			if(address1code.equals(address2code)) return 1;
			else return -1;
		}
	}
	
}
