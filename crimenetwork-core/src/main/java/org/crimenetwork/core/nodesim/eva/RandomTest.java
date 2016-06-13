package org.crimenetwork.core.nodesim.eva;

import java.util.Random;


public class RandomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();//默认构造方法
		int count=0;
	    while(count<10){
	    	double value=random.nextDouble();
	    	if(value>0.8&&value<0.98){
	    		System.out.println(value);
	    		count++;
	    	}
	    }

	}

}
