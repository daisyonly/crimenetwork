package org.crimenetwork.mongodb;


import java.io.InputStream;

import org.crimenetwork.mongodb.entity.Case;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.InputStreamResource;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		
		String[] springConfig2  = 
			{
				"classpath:/database/mangodb/datasource1.xml" 
			};
		
		App app=new App();
		
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("datasource111.xml");
		System.out.println(in);
		ApplicationContext context2 = 
				new GenericXmlApplicationContext(new InputStreamResource(in));
				//new classpath
				//new classpa
				//new ClassPathXmlApplicationContext("classpath*:datasource111.xml");
		//mongoTemplate
		context2.getBean("mongoTemplate");
		//BasicRepository<Case> caseDao = (BasicRepository<Case>) context2.getBean("caseDao");
		//System.out.println(caseDao);
		
		

		
        System.out.println( "Hello World!" );
    }
}
