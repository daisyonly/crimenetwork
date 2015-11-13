package org.crimenetwork.mongodb;




import org.crimenetwork.mongodb.entity.Case;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;


/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings({ "resource", "unchecked" })
	public static void main( String[] args )
    {
		
		String[] springConfig2  = 
			{
				"classpath:/database/mongodb/datasourse.xml" 
			};
		//"classpath:database/mongodb/datasourse.xml" 
		ApplicationContext context2 = new ClassPathXmlApplicationContext(springConfig2);
		//mongoTemplate
		
		BasicRepository<Case> caseDao = (BasicRepository<Case>) context2.getBean("caseDao");
		System.out.println(caseDao);
		MongoTemplate tt = (MongoTemplate) context2.getBean("mongoTemplate");
		System.out.println(tt);
		Case ca = new Case();
		ca.setCaseId("123");
		ca.setBriefInfo("hello");
		caseDao.saveAndUpdate(ca);
		

		
        System.out.println( "Hello World!" );
    }
}
