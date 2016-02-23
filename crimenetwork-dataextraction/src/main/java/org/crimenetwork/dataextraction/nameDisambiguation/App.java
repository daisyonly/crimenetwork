package org.crimenetwork.dataextraction.nameDisambiguation;
import org.crimenetwork.dataextraction.nameDisambiguation.clusterService.ClusterProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String[] springConfig  = 
			{	"classpath:dataextraction/applicationContext.xml", 
			};
		
		@SuppressWarnings("resource")
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
			
		//SuspectNameStatistics suspectNameStatistics = (SuspectNameStatistics) context.getBean("suspectNameStatistics");

		//suspectNameStatistics.run();
		
		ClusterProcessor clusterProcessor = (ClusterProcessor) context.getBean("clusterProcessor");

		//clusterProcessor.process();
		clusterProcessor.outputCluster(clusterProcessor.process());
		
	
        System.out.println( "Hello World!" );
    }
}
