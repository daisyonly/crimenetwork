package org.crimenetwork.statistics;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class Dalian {
	private String Address="http://133.133.133.29:8479/FakeNoteServer/";//所有接口的调用地址头

	public final static String TYPE_WHITE="White";
	public final static String TYPE_PURPLE="Purple";
	public final static int QUEUE_TYPE_ALL=2;
	public final static int QUEUE_TYPE_ZHENG=0;
	public final static int QUEUE_TYPE_fan=1;
	
	public String NewQueueTask(Long sampleId,String targetList,String type,String valueModuleType,int queueType){		
		if(targetList==null || targetList.equals(""))return "-1";
		if(valueModuleType==null)valueModuleType="Hundred05";
		String s_url = Address+"NewQueueTask";
		String s_para="{\"sampleId\":\""+sampleId.toString()+"\",\"targetList\":\""+targetList+"\",\"type\":\""
			+type+"\",\"queueType\":\""+queueType+"\",\"valueModuleType\":\""+valueModuleType+"\"}";
		String taskId = this.getPost(s_url, s_para);
		taskId=taskId.replaceAll("\"", "");
		return taskId;
	}
	
	public String GetQueueList(String taskId){	
		String s_url = Address+"GetQueueList?taskId="+taskId;
		String result = this.getGet(s_url);
		result=result.replaceAll("\"", "");
		return result;
	}
	
	public int GetTaskProgress(String taskId){
		String s_url = Address+"GetTaskProgress?taskId="+taskId;
		String result = this.getGet(s_url);
		result=result.replaceAll("\"", "");
		result=result.replaceAll("\"", "");
		return Integer.parseInt(result);
	}
    
    //return the result of a url
    //return -1 if nothing to return
    private String getGet(String s_url){
    	URL url;
        HttpURLConnection httpURLConn;
        System.out.println(s_url);
        String result="";
        try
        {
            url = new  URL(s_url);
            httpURLConn= (HttpURLConnection)url.openConnection();
            httpURLConn.setDoOutput(true);
            httpURLConn.setRequestMethod("GET");
            httpURLConn.setIfModifiedSince(999999999);
            httpURLConn.connect();
            InputStream in =httpURLConn.getInputStream();
            BufferedReader bd = new BufferedReader(new InputStreamReader(in));
            result=bd.readLine();
            if(result.equals(""))result="-1";           
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
    
    private String getPost(String s_url,String s_para){
    	String result ="";
    	System.out.println("s_url:"+s_url);
    	System.out.println("s_para"+s_para);
    	
    	try{
    	URL url = new URL(s_url);  
        HttpURLConnection connection= (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStreamWriter out = new OutputStreamWriter(connection  
                .getOutputStream(), "UTF-8");//UTF-8 or 8859_1 
        out.write(s_para); //post的关键所在！  
        // remember to clean up  
        out.flush();  
        out.close();  
        /** 
         * 这样就可以发送一个看起来象这样的POST：  
         * POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT: 
         * text/plain Content-type: application/x-www-form-urlencoded 
         * Content-length: 99 username=bob password=someword 
         */  
        // 一旦发送成功，用以下方法就可以得到服务器的回应：  
        InputStream l_urlStream;
        connection.connect();
        l_urlStream = connection.getInputStream();  
        // 传说中的三层包装阿！  
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(  
                l_urlStream));
        String temp;
        while((temp=l_reader.readLine())!=null)
        {
            result +=temp;
        }
        if(result.equals(""))result="-1";
    	}catch(Exception e){
    		 e.printStackTrace();
    	}
    	System.out.println(result);
        return result;
    }
}