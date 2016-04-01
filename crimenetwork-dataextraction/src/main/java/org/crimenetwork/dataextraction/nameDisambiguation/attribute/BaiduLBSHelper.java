package org.crimenetwork.dataextraction.nameDisambiguation.attribute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.constraints.Null;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

//doc: http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
public class BaiduLBSHelper {
     static String ak1="GobftU8HPocvrXyEAbCYOyIKDKu3oFsI";
     static String ak2="BFd5818f3c636267bd99b744d4725a20";
    // static String ak3="1YKZoZCz7Be5ZZko5smGLYfFEGaHhLs1";
    // static String ak4="61twUIzlfUQesIsv8hu5gMVyyOgGj9Ps";
     static String curAk="BFd5818f3c636267bd99b744d4725a20";
     static ObjectMapper mapper = new ObjectMapper();
	// http://api.map.baidu.com/place/v2/suggestion?query=天安门&region=131&output=json&ak=E4805d16520de693a3fe707cdc962045
	public static void getSuggestion(String query) {
		String urlString = "http://api.map.baidu.com/place/v2/suggestion";
		urlString += "?query=" + query
				+ "&region=全国&output=json&ak=GobftU8HPocvrXyEAbCYOyIKDKu3oFsI";
		try {
			// urlString=URLEncoder.encode(urlString, "UTF-8");
			URI uri = new URI(urlString);
			SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
			ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.POST);
			ClientHttpResponse res = chr.execute();
			InputStream is = res.getBody(); // 获得返回数据,注意这里是个流
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = "";
			while ((str = br.readLine()) != null) {
				System.out.println(str);// 获得页面内容或返回内容
			}

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getGeocoder(String query) {
		String urlString = "http://api.map.baidu.com/geocoder/v2/";
		urlString += "?address=" + query
				+ "&output=json&ak="+curAk;
		try {

			URI uri = new URI(urlString);
			SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
			ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.POST);
			ClientHttpResponse res = chr.execute();
			HttpStatus states = res.getStatusCode();
			if (states.is2xxSuccessful()) {
				InputStream is = res.getBody(); // 获得返回数据,注意这里是个流
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String str = "";
				String reString = "";
				while ((str = br.readLine()) != null) {
					// System.out.println(str);//获得页面内容或返回内容
					reString = reString + str;
				}
				JsonNode node = mapper.readTree(reString);
				String status=node.get("status").toString();
				if(Integer.parseInt(status)==302){
					System.out.println("baidu 302");
				}
				return reString;
			}
		} catch (URISyntaxException e1) {
			System.out.println("query:" + query);
			e1.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public static String changeAk(String query) {
		String urlString = "http://api.map.baidu.com/geocoder/v2/";
		urlString += "?address=" + query
				+ "&output=json&ak="+curAk;
		try {

			URI uri = new URI(urlString);
			SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
			ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.POST);
			ClientHttpResponse res = chr.execute();
			HttpStatus states = res.getStatusCode();
			if (states.is2xxSuccessful()) {
				InputStream is = res.getBody(); // 获得返回数据,注意这里是个流
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String str = "";
				String reString = "";
				while ((str = br.readLine()) != null) {
					// System.out.println(str);//获得页面内容或返回内容
					reString = reString + str;
				}
				JsonNode node = mapper.readTree(reString);
				String status=node.get("status").toString();
				if(Integer.parseInt(status)==302){
					return null;
				}
				return reString;
			}else{
				System.out.println("baidu query error:" + query);
			}
		} catch (URISyntaxException e1) {
			System.out.println("query:" + query);
			e1.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getGeocoder("河南潢川县黄寺岗镇白术村北杨营组"));
	}

}
