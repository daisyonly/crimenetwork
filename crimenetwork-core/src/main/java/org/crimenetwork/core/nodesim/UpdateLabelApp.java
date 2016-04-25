package org.crimenetwork.core.nodesim;

import java.util.ArrayList;
import java.util.List;

import org.crimenetwork.core.nodesim.eva.EvaluateHelper;
import org.crimenetwork.core.nodesim.model.DataReader;
import org.crimenetwork.core.nodesim.service.UpdateLabelService;
import org.crimenetwork.core.service.ExeHelper;
import org.crimenetwork.core.utility.FileUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class UpdateLabelApp 
{
    @SuppressWarnings("resource")
	public static void main( String[] args )
    {
    	
		
    	String[] springConfig  = 
			{	"classpath:core/applicationContext.xml", 
			};
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		UpdateLabelService updateLabelService = 
				(UpdateLabelService) context.getBean("updateLabelService");
		char flags[]=new char[3];
		flags[0]='S';
		flags[1]='J';
		flags[2]='C';
		
		//String trainInputFile="D:\\毕业设计\\svmrank\\data\\train.txt";
		//String testInputFile="D:\\毕业设计\\svmrank\\data\\test.txt";
		
		for(int i=0;i<3;i++){
			char flag=flags[i];
			String oraTrainDataFile="D:\\毕业设计\\svmrank\\data\\数据_4.11\\"+flag+"trainData.dat";
			String oraTrainOrderFile="D:\\毕业设计\\svmrank\\data\\数据_4.11\\"+flag+"TrainOrderData.dat";
			
			
			String oraTestDataFile="D:\\毕业设计\\svmrank\\data\\数据_4.11\\"+flag+"TestData.dat";
			String oraTestOrderFile="D:\\毕业设计\\svmrank\\data\\数据_4.11\\"+flag+"TestOrderData.dat";
			
			
			String trainDataFile="D:\\毕业设计\\svmrank\\data\\"+flag+"trainData.dat";
			String trainOrderFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TrainOrderData.dat";
			
			
			String testDataFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TestData.dat";
			String testOrderFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TestOrderData.dat";
			updateLabelService.update(flag, oraTrainDataFile, oraTrainOrderFile, trainDataFile, trainOrderFile);
			
			updateLabelService.update(flag, oraTestDataFile, oraTestOrderFile, testDataFile, testOrderFile);
			
		}
		System.out.println( "Hello World!" );
    }
    
    
}
