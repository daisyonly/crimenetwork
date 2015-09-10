package org.crimenetwork.mongodb;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({ MongoConfiguration.class ,MongoBeansConfiguration.class})
@ComponentScan("com.imoney.mongodb")
public class MongoRootConfiguration {

	
}
