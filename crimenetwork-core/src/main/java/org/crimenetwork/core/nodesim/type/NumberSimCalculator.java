package org.crimenetwork.core.nodesim.type;

public class NumberSimCalculator {
	
	public static double calculate(Float number1,Float number2){
		if(number1==null||number2==null) return 0;
		float distance = Math.abs(number1-number2);
		if(distance < 10) return 1;
		
		return 0;	
	}
	

}
