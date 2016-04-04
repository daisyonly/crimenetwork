package org.crimenetwork.core.nodesim.model;

import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
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
		
		
		DataGenerator dataGenerator = (DataGenerator) context.getBean("dataGenerator");
        String inputFileString="C:\\Users\\Daisy\\Desktop\\input.txt";
        String outPutFileString="C:\\Users\\Daisy\\Desktop\\train.dat";
        String outPutFilelable="C:\\Users\\Daisy\\Desktop\\label.csv";
        //dataGenerator.run(inputFileString, outPutFileString);
        dataGenerator.outputLabelData("J16775", outPutFilelable);
		
        System.out.println( "Hello World!" );
    }
}
