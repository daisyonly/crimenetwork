package org.crimenetwork.oracle;

import java.util.List;

import org.crimenetwork.oracle.entity.cases.CaseInfo;
import org.crimenetwork.oracle.service.CaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;


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
				"classpath:/database/oracle/datasourse.xml" 
			};
	
		ApplicationContext context2 = new ClassPathXmlApplicationContext(springConfig2);

		
		CaseService caseDao = (CaseService) context2.getBean("caseService");
		System.out.println(caseDao);
		Page<CaseInfo> page=caseDao.findAll(1);
		List<CaseInfo> list = page.getContent();
	
		

		
        System.out.println( "Hello World!" );
    }
}
