package org.crimenetwork.statistics;

import org.crimenetwork.oracle.repository.JiabiBaseDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimApp {
	public static void main( String[] args )
    {
    	String[] springConfig  = 
			{	"classpath:dataextraction/applicationContext.xml", 
			};
		
		@SuppressWarnings("resource")
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);		
		
		
		JiabiSimService casesStatistics = (JiabiSimService) context.getBean("jiabiSimService");

		casesStatistics.runAll("C:\\Users\\Daisy\\Desktop\\mapsim.txt");
		//JiabiBaseDao jiabiBaseDao = (JiabiBaseDao) context.getBean("jiabiBaseDao");
	    //Long id=(long) 736;
		//jiabiBaseDao.findByFmid(id);
        System.out.println( "Hello World!" );
    }

}
