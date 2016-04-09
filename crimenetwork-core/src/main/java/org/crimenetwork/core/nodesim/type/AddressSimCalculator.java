package org.crimenetwork.core.nodesim.type;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.crimenetwork.core.baiduGis.BaiduLBSHelper;
import org.crimenetwork.core.baiduGis.GeocodingResult;
import org.crimenetwork.core.utility.LCSHelper;


public class AddressSimCalculator {
	
	private static double EARTH_RADIUS = 6378.137;// 地球半径
	
	
	public static double calculate(String code1,String code2,String ad1,String ad2){
		if(code1==null||code2==null) return 0;
		if(code1.equals("999999")||code2.equals("999999")){	
			if(ad1==null||ad2==null) return 0;
			else{
				ad1=ad1.trim();
				ad2=ad2.trim();
				int lcscount=LCSHelper.LCS(ad1, ad2);
				int maxLength=Math.max(ad1.length(), ad2.length());
				if(lcscount*1>=maxLength) return 5;
				if(lcscount*2>=maxLength) return 4;
				if(lcscount*3>=maxLength) return 3;
				if(lcscount*4>=maxLength) return 2;
				if(lcscount*5>=maxLength) return 1;
				return 0;
			}
		}else{
			if(code1.equals(code2)) return 5;
			else return 0;
		}
		
	}
	
	public static double calculate2(String address1,String address2){
		if(address1==null||address2==null) return 0;
		ObjectMapper mapper = new ObjectMapper();
		String jsonString1=BaiduLBSHelper.getGeocoder(address1);
		String jsonString2=BaiduLBSHelper.getGeocoder(address2);
		
		try {
			JsonNode node1 = mapper.readTree(jsonString1);
			JsonNode node2 = mapper.readTree(jsonString2);
			String status1=node1.get("status").toString();
			String status2=node2.get("status").toString();
			if(Integer.parseInt(status1)==0&&Integer.parseInt(status2)==0){
				GeocodingResult result1 = mapper.readValue(jsonString1, GeocodingResult.class);
				GeocodingResult result2 = mapper.readValue(jsonString2, GeocodingResult.class);
				double dis=getDistance(result1,result2);
				System.out.println(dis);
				if(dis<100) return 5;
				if(dis<300) return 4;
				if(dis<400) return 3;
				if(dis<500) return 2;
				if(dis<1000) return 1;
				return 0;
			}
			else{
				return 0;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	private static double getDistance(GeocodingResult result1,GeocodingResult result2){
		return getDistance(result1.result.location.lat, result1.result.location.lng, 
				result2.result.location.lat, result2.result.location.lng);
	}
	
	private static double getDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	
	public static void main(String[] args) {
		//BaiduLBSHelper.testSuggestion("广州市黄埔区大沙姬堂");			
		calculate2("龙岩","宁德");
	
	}
	
}
