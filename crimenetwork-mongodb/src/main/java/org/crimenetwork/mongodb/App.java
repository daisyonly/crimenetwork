package org.crimenetwork.mongodb;




import java.util.List;

import org.crimenetwork.mongodb.entity.currency.MJiabiBaseInfo;
import org.crimenetwork.mongodb.repository.BasicRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


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

		BasicRepository<MJiabiBaseInfo> jiabiDao = (BasicRepository<MJiabiBaseInfo>) context2.getBean("jiabiDao");		
		
		List<MJiabiBaseInfo> result=jiabiDao.findAllByAttributeName("X8G8450597", "guanzihao");
		for(MJiabiBaseInfo m : result){
			System.out.println(m);
		}

		
        System.out.println( "Hello World!" );
    }
}
