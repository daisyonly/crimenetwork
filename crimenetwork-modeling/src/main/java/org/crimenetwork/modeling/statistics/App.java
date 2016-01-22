package org.crimenetwork.modeling.statistics;
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
		
		
		//JiabiStatistics jiabiStatistics = (JiabiStatistics) context.getBean("jiabiStatistics");

		//jiabiStatistics.run();
		
		//SuspectStatistics suspectStatistics = (SuspectStatistics) context.getBean("suspectStatistics");

		//suspectStatistics.run();
		CasesStatistics casesStatistics = (CasesStatistics) context.getBean("casesStatistics");

		casesStatistics.run();
		
	
        System.out.println( "Hello World!" );
    }
}
