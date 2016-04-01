package org.crimenetwork.dataextraction.nameDisambiguation;
import org.crimenetwork.dataextraction.nameDisambiguation.clusterService.ClusterProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Hello world!
 *
 */
public class EvaluationApp 
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
		clusterProcessor.init();
		double sim=-1;
		while(sim<=1){
			double sum=0;
			for(int i=0;i<33;i++){
				try {
					double result = clusterProcessor.evaluateResult(clusterProcessor.process(i,sim), i);
					sum+=result;
					//System.out.println(i+":" +result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(sim+":"+sum/33);
			sim=sim+0.05;
			
		}
		

	
        System.out.println( "Hello World!" );
    }
}
