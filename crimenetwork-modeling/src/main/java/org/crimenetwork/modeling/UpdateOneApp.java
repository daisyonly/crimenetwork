package org.crimenetwork.modeling;


import org.crimenetwork.modeling.service.CaseService;
import org.crimenetwork.modeling.service.CurrencyService;
import org.crimenetwork.modeling.service.SuspectService;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.repository.CrimeCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class UpdateOneApp 
{
    public static void main( String[] args )
    {
    	String[] springConfig  = 
			{	"classpath:modeling/applicationContext.xml", 
			};
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		
		//CrimeCaseRepository crimeCaseRepository=(CrimeCaseRepository) context.getBean("crimeCaseRepository");
		
		//CrimeCase cc=crimeCaseRepository.findByCaseId("A4401111502002006120001");
		CaseService caseService = (CaseService) context.getBean("caseService");

		caseService.updateCounterfeitMoney();
		//Long id=(long) 44577;
		//caseService.updateOne(id);
		//CurrencyService currencyService = (CurrencyService) context.getBean("currencyService");

		//currencyService.moveALLData();
		
		//SuspectService suspectService = (SuspectService) context.getBean("suspectService");

		//suspectService.moveALLData();
		
        System.out.println( "Hello World!" );
    }
}
