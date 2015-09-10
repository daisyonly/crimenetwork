package org.crimenetwork.mongodb;

import org.crimenetwork.mongodb.entity.*;
import org.crimenetwork.mongodb.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MongoBeansConfiguration {
	/*
	 * user
	 */
	@Bean
	public DBConvertor<User> userConvertor(){
		return new DBConvertor<>(User.class);
	}
	@Bean
	public BasicRepository<User> userDao(){
		return new BasicRepository<User>(User.class,userConvertor());
	}
	/*
	 * record
	 */
	@Bean
	public DBConvertor<Record> recordConvertor(){
		return new DBConvertor<>(Record.class);
	}
	@Bean
	public BasicRepository<Record> recordDao(){
		return new BasicRepository<Record>(Record.class,recordConvertor());
	}
	/*
	 * task
	 */
	@Bean
	public DBConvertor<Task> taskConvertor(){
		return new DBConvertor<>(Record.class);
	}
	@Bean
	public BasicRepository<Task> taskDao(){
		return new BasicRepository<Task>(Task.class,taskConvertor());
	}
}
