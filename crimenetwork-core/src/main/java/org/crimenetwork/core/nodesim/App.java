package org.crimenetwork.core.nodesim;

import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("resource")
	public static void main( String[] args )
    {
    	String[] springConfig  = 
			{	"classpath:core/applicationContext.xml", 
			};
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		
		
		CounterfeitMoneyRepository counterfeitMoneyRepository = (CounterfeitMoneyRepository) context.getBean("counterfeitMoneyRepository");

		//CounterfeitMoney cm=counterfeitMoneyRepository.findByPiaoyangNumber("CNY005019900FT008577120541135720110620");
		CounterfeitMoney cm1=counterfeitMoneyRepository.findByFmid((long) 16775);
		CounterfeitMoney cm2=counterfeitMoneyRepository.findByFmid((long) 16926);
		//MetaPathFeatureGenerator test=new MetaPathFeatureGenerator();
		
		//test.outputMetaPathFeature(test.generate(cm));
		SimRankFeatureGenerator hehe= new SimRankFeatureGenerator();
		System.out.println(hehe.outputFeature('J', hehe.generate(cm1, cm2)));
		
        System.out.println( "Hello World!" );
    }
}
