package org.crimenetwork.core.nodesim.type;

public class NumberSimCalculator {
	
	public static double calculateSeizedAmount(Float number1,Float number2){
		if(number1==null||number2==null) return 0;
		float distance = Math.abs(number1-number2);
		if(distance < 10) return 5;
		if(distance < 20) return 4;
		if(distance < 30) return 3;
		if(distance < 40) return 2;
		if(distance < 50) return 1;
		return 0;	
	}
	
	public static double calculate(Double number1,Double number2){
		if(number1==null||number2==null) return 0;
		double distance = Math.abs(number1-number2);
		if(distance < 10) return 1;
		
		return 0;	
	}

	public static double calculateCurrencyQuantity(Double number1,
			Double number2) {
		if(number1==null||number2==null) return 0;
		double distance = Math.abs(number1-number2);
		if(distance < 10) return 5;
		if(distance < 20) return 4;
		if(distance < 30) return 3;
		if(distance < 40) return 2;
		if(distance < 50) return 1;
		return 0;
		
	}

	public static double calculateCurrencyThick(String thick1, String thick2) {
		// TODO Auto-generated method stub
		if(thick1==null||thick2==null) return 0;
		if(thick1.endsWith("mm")||thick1.endsWith("MM")){
			thick1=thick1.substring(0, thick1.length()-2);
		}
		if(thick2.endsWith("mm")||thick2.endsWith("MM")){
			thick2=thick2.substring(0, thick2.length()-2);
		}
		double number1=Double.parseDouble(thick1);
		double number2=Double.parseDouble(thick2);
		double distance = Math.abs(number1-number2);
		if(distance < 0.01) return 5;
		if(distance < 0.05) return 4;
		if(distance < 0.1) return 3;
		if(distance < 0.5) return 2;
		if(distance < 1) return 1;
		return 0;
	}
	

}
