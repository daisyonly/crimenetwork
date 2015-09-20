package org.crimenetwork.oracle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.service.CaseService;
import org.crimenetwork.oracle.service.SuspectService;
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
		Page<CaseBaseInfo> page=caseDao.findAll(1);
		List<CaseBaseInfo> list = page.getContent();
		
		SuspectService suspectDao = (SuspectService) context2.getBean("suspectService");
		System.out.println(suspectDao);
		Page<SuspectBaseInfo> page2=suspectDao.findAll(1);
		List<SuspectBaseInfo> list2 = page2.getContent();
	    Set<CaseBaseInfo> test1=new HashSet<CaseBaseInfo>();
		test1.add(new CaseBaseInfo());

		
        System.out.println( "Hello World!" );
    }
}
