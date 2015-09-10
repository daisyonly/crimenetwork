package org.crimenetwork.mongodb.utils;

import org.crimenetwork.mongodb.MongoRootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringConfigSingleton {
	private static SpringConfigSingleton unique;
	private ApplicationContext context;
	private SpringConfigSingleton(){
		context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	}
	public static ApplicationContext getContext(){
		if(unique == null){
			unique = new SpringConfigSingleton();
		}
		return unique.context;
	}
	public static Object getBean(String name){
		return getContext().getBean(name);
	}
}
