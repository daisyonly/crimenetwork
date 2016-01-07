package org.crimenetwork.currencysim;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimApp {
	public static void main( String[] args )
    {
    	String[] springConfig  = 
			{	"classpath:currencysim/applicationContext.xml", 
			};
		
		@SuppressWarnings("resource")
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);		
		
		
		JiabiSimService casesStatistics = (JiabiSimService) context.getBean("jiabiSimService");
        String dataPath="C:\\Users\\Daisy\\Desktop\\mapsim.txt";
        String logPath="C:\\Users\\Daisy\\Desktop\\simlog.txt";
		casesStatistics.runAll(dataPath,logPath);
		
        System.out.println( "Hello World!" );
    }

}
