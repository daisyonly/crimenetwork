package org.crimenetwork.core.nodesim.type;

import java.util.Date;

public class DateSimCalculator {
	
	public static double calculate(Date date1,Date date2){
		if(date1==null||date2==null) return 0;
		long distance = Math.abs(date1.getTime()-date2.getTime());
		long day=distance/3600000;
		if(day<365) return 5;
		
		if(day<(365*3)) return 3;
		if(day<(365*5)) return 2;
		return 1;	
	}
	

}
