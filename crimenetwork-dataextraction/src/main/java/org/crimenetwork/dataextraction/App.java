package org.crimenetwork.dataextraction;


import org.crimenetwork.dataextraction.service.CaseService;
import org.crimenetwork.dataextraction.service.SuspectService;
import org.crimenetwork.mongodb.entity.Case;
import org.crimenetwork.mongodb.repository.BasicRepository;
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
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		
		
		//CaseService caseService = (CaseService) context.getBean("caseService");

		//caseService.findAll(1);
		
		SuspectService suspectService = (SuspectService) context.getBean("suspectService");

		suspectService.moveALLData();
        System.out.println( "Hello World!" );
    }
}
