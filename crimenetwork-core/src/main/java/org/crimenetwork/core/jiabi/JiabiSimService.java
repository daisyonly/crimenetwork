package org.crimenetwork.core.jiabi;

import java.util.HashMap;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class JiabiSimService {
	
	public HashMap<String, Double> getSimJiabis(double threshold, Long fmid){
		HashMap<String, Double> res=new HashMap<String, Double>();
		
		return res;
		
	}
	
	public HashMap<JiabiSimPair, Double> test(){
		return null;
		
	}
	public static void main(String[] args) {
		Pair<String, String> aPair=new ImmutablePair<String, String>("a","b");
		Pair<String, String> bPair=new ImmutablePair<String, String>("a","b");
		System.out.println(aPair.equals(bPair));
		String aString="abs";
		String bString="abs";
		System.out.println(aString==bString);
	}

}
