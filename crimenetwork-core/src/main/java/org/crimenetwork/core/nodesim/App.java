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
		
		DataReader dataReader = 
				(DataReader) context.getBean("dataReader");
		String trainInputFile="D:\\毕业设计\\svmrank\\data\\train.txt";
		String testInputFile="D:\\毕业设计\\svmrank\\data\\test.txt";
		char flag='S';
		String trainDataFile="D:\\毕业设计\\svmrank\\data\\"+flag+"trainData2.dat";
		String trainOrderFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TrainOrderData.dat";
		String modelFilePath="D:\\毕业设计\\svmrank\\data\\"+flag+"model.dat";
		String predictionsPath="D:\\毕业设计\\svmrank\\data\\"+flag+"predictions";
		
		String testDataFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TestData.dat";
		String testOrderFile="D:\\毕业设计\\svmrank\\data\\"+flag+"TestOrderData.dat";
		//dataReader.readData(trainInputFile, trainDataFile, trainOrderFile, flag);
		//dataReader.readData(testInputFile, testDataFile, testOrderFile, flag);
		//ExeHelper.train(trainDataFile, modelFilePath);
		//ExeHelper.rank(testDataFile, modelFilePath, predictionsPath);
		EvaluateHelper.run(testOrderFile, predictionsPath);
		//String testLineString="S19876,S19867,S19868,S198711,C29759,C29735,C29871,C29732,S20321,S21209,S21297,S21295,S21296,C29708,S21278,C29068,S21299,C29764,S21218,C29869,S21243,S21285,S21286,S21287,";
		//dataReader.test(testLineString, 'S');
		System.out.println( "Hello World!" );
    }
}
