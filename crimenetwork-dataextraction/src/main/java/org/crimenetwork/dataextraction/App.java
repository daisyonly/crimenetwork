package org.crimenetwork.dataextraction;

import org.crimenetwork.dataextraction.service.Test;
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
				"classpath*:database/oracle/datasource.xml",
				"classpath*:database/mangodb/datasource.xml" 
			};
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		
		String[] springConfig2  = 
			{
				"classpath*:database/mangodb/datasource.xml" 
			};
		
		ApplicationContext context2 = 
				new ClassPathXmlApplicationContext(springConfig2);
		
		
		BasicRepository<Case> caseDao = (BasicRepository<Case>) context2.getBean("caseDao");
		System.out.println(caseDao);
		
		Test gotest = (Test) context.getBean("gotest");

		gotest.findAll(1);
        System.out.println( "Hello World!" );
    }
}
