package org.crimenetwork.dataextraction.nameDisambiguation;

import org.crimenetwork.dataextraction.nameDisambiguation.clusterService.SuspectRelationUpdater;
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
			
		
		SuspectRelationUpdater suspectRelationUpdater = (SuspectRelationUpdater) context.getBean("suspectRelationUpdater");

		suspectRelationUpdater.update();

        System.out.println( "Hello World!" );
    }
}
