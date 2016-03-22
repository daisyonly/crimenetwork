package org.crimenetwork.dataextraction.currencySim;

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
			
		
		CurrencySimProcessor currencySimProcessor = (CurrencySimProcessor) context.getBean("currencySimProcessor");

		currencySimProcessor.run();
		
		
	
        System.out.println( "Hello World!" );
    }
}
