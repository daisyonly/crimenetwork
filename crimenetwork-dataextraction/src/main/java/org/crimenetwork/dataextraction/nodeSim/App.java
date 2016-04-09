package org.crimenetwork.dataextraction.nodeSim;

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
			
		
		SuspectCounter suspectCounter = (SuspectCounter) context.getBean("suspectCounter");

		suspectCounter.countALLData();
		
		
	
        System.out.println( "Hello World!" );
    }
}
