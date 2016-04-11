package org.crimenetwork.core.nodesim;

import org.crimenetwork.core.nodesim.eva.EvaluateHelper;
import org.crimenetwork.core.nodesim.model.DataReader;
import org.crimenetwork.core.service.ExeHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("resource")
	public static void main( String[] args )
    {
    	String[] springConfig  = 
			{	"classpath:core/applicationContext.xml", 
			};
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		
		
		//CounterfeitMoneyRepository counterfeitMoneyRepository = (CounterfeitMoneyRepository) context.getBean("counterfeitMoneyRepository");

		//CounterfeitMoney cm=counterfeitMoneyRepository.findByPiaoyangNumber("CNY005019900FT008577120541135720110620");
		//CounterfeitMoney cm1=counterfeitMoneyRepository.findByFmid((long) 16775);
		//CounterfeitMoney cm2=counterfeitMoneyRepository.findByFmid((long) 16926);
		//MetaPathFeatureGenerator test=new MetaPathFeatureGenerator();
		
		//test.outputMetaPathFeature(test.generate(cm));
		//SimRankFeatureGenerator hehe= new SimRankFeatureGenerator();
		//System.out.println(hehe.outputFeature('J', hehe.generate(cm1, cm2)));
		char flags[]=new char[3];
		flags[0]='S';
		flags[1]='J';
		flags[2]='C';
		
		DataReader dataReader = 
				(DataReader) context.getBean("dataReader");
		
		String trainInputFile="D:\\毕业设计\\svmrank\\data\\train.txt";
		String testInputFile="D:\\毕业设计\\svmrank\\data\\test.txt";
		
		for(int i=0;i<3;i++){
			char flag=flags[i];
			String trainDataFile="D:\\毕业设计\\svmrank\\data\\"+flag+"trainData.dat";
			String trainOrderFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TrainOrderData.dat";
			String modelFilePath="D:\\毕业设计\\svmrank\\data\\"+flag+"model.dat";
			String predictionsPath="D:\\毕业设计\\svmrank\\data\\"+flag+"predictions";
			
			String testDataFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TestData.dat";
			String testOrderFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TestOrderData.dat";
			
			//dataReader.readData(trainInputFile, trainDataFile, trainOrderFile, flag);
			//dataReader.readData(testInputFile, testDataFile, testOrderFile, flag);
			ExeHelper.train(trainDataFile, modelFilePath);
			ExeHelper.rank(testDataFile, modelFilePath, predictionsPath);
			EvaluateHelper.run(testOrderFile, predictionsPath);
		}
		
		
		/*char flag='C';
		String trainDataFile="D:\\毕业设计\\svmrank\\data\\"+flag+"trainData.dat";
		String trainOrderFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TrainOrderData.dat";
		String modelFilePath="D:\\毕业设计\\svmrank\\data\\"+flag+"model.dat";
		String predictionsPath="D:\\毕业设计\\svmrank\\data\\"+flag+"predictions";
		
		String testDataFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TestData.dat";
		String testOrderFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TestOrderData.dat";
		*/
		//dataReader.readData(trainInputFile, trainDataFile, trainOrderFile, flag);
		
		//dataReader.readData(testInputFile, testDataFile, testOrderFile, flag);
		//ExeHelper.train(trainDataFile, modelFilePath);
		//ExeHelper.rank(testDataFile, modelFilePath, predictionsPath);
		//EvaluateHelper.run(testOrderFile, predictionsPath);
		//String testLineString="J17057,C32383,C44419,C43758,S37631,S24916,S38584,S37238,J17181,J16660,S38033,C43941,J16405,J16504,C44577,C43452,J16214,";
		//dataReader.test(testLineString, 'C');
		
		System.out.println( "Hello World!" );
    }
}
