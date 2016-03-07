package org.crimenetwork.core.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ExeHelper {
	public static void main(String[] args) {
		try {
			String folder="D:\\毕业设计\\svmrank\\svm_rank_windows\\";
			Process process = new ProcessBuilder(folder+"svm_rank_classify.exe",folder+"test.dat",folder+"model.dat",folder+"predictions").start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			System.out.printf("Output of running %s is:", Arrays.toString(args));

			while ((line = br.readLine()) != null) {
			  System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
