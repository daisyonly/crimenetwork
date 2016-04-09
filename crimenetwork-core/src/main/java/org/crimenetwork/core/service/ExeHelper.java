package org.crimenetwork.core.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ExeHelper {
	
	public static void train(String trainDataPath,String modelFilePath){
		try {
			String folder="D:\\毕业设计\\svmrank\\svm_rank_windows\\";
			Process process = new ProcessBuilder(folder+"svm_rank_learn.exe","-c","20.0",trainDataPath,modelFilePath).start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			System.out.printf("Output of running is:");

			while ((line = br.readLine()) != null) {
			  System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void rank(String trainDataPath,String modelFilePath,String predictionsPath){
		try {
			String folder="D:\\毕业设计\\svmrank\\svm_rank_windows\\";
			Process process = new ProcessBuilder(folder+"svm_rank_classify.exe",trainDataPath,modelFilePath,predictionsPath).start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			System.out.printf("Output of running is:");

			while ((line = br.readLine()) != null) {
			  System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	

}
