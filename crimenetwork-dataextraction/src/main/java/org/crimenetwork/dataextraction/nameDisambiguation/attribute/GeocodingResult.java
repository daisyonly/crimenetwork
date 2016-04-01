package org.crimenetwork.dataextraction.nameDisambiguation.attribute;

public class GeocodingResult {
	/*
	 * status Int 返回结果状态值， 成功返回0，其他值请查看下方返回码状态表。
	 * location object 经纬度坐标
	 *      lat   float  纬度值
	 *      lng   float  经度值
	 * precise  Int 位置的附加信息，是否精确查找。1为精确查找，即准确打点；0为不精确，即模糊打点。
	 * confidence  Int  可信度，描述打点准确度
	 * level     string   地址类型
	 */
	public int status;
	public BaiduGeoResult result;
	
	public class BaiduGeoResult{
		public BaiduLocation location;
		public  int precise;
		public  int confidence;
		public String level;
		
		public class BaiduLocation{
			public float lat;
			public float lng;
		}
	}
	
	

}
