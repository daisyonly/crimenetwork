package org.crimenetwork.core.baiduGis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

//doc: http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
public class BaiduLBSHelper {
	
	//http://api.map.baidu.com/place/v2/suggestion?query=天安门&region=131&output=json&ak=E4805d16520de693a3fe707cdc962045
		public static void getSuggestion(String query){
			String urlString = "http://api.map.baidu.com/place/v2/suggestion";
			urlString+= "?query="+query+"&region=全国&output=json&ak=GobftU8HPocvrXyEAbCYOyIKDKu3oFsI";
			//System.out.println(urlString);
		    try {
		    	//urlString=URLEncoder.encode(urlString, "UTF-8");
		    	URI uri = new URI(urlString);
		    	//System.out.println(urlString);
	            //System.out.println(uri.toURL());
	            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
	            ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.POST);
	            ClientHttpResponse res = chr.execute();
	            InputStream is = res.getBody(); //获得返回数据,注意这里是个流
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            String str = "";
	            while((str = br.readLine())!=null){
	                System.out.println(str);//获得页面内容或返回内容
	            }
	             
	        } catch (URISyntaxException e1) {         
	            e1.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		} 
		
		public static String getGeocoder(String query){
			String urlString = "http://api.map.baidu.com/geocoder/v2/";
			urlString+= "?address="+query+"&output=json&ak=GobftU8HPocvrXyEAbCYOyIKDKu3oFsI";
		    try {
		    	
		    	URI uri = new URI(urlString); 	
	            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
	            ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.POST);
	            ClientHttpResponse res = chr.execute();
	            InputStream is = res.getBody(); //获得返回数据,注意这里是个流
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            String str = "";
	            String reString="";
	            while((str = br.readLine())!=null){
	                System.out.println(str);//获得页面内容或返回内容
	                reString=reString+str;
	            }
	             return reString;
	        } catch (URISyntaxException e1) {
	            
	        	System.out.println("query:"+query);
	            e1.printStackTrace();
	        } catch (IOException e) {
	           
	            e.printStackTrace();
	        }
			return null;
		} 

}
