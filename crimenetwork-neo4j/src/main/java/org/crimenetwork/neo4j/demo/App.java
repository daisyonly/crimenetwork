package org.crimenetwork.neo4j.demo;

import java.io.File;
import java.io.IOException;

import org.neo4j.graphdb.Transaction;
import org.neo4j.io.fs.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Component;

@Component
public class App {
	@Autowired
	PersonRepository personRepository;

	@Autowired
	GraphDatabase graphDatabase;

	public void work() {
		Person greg = new Person("Greg");
		Person roy = new Person("Roy");
		Person craig = new Person("Craig");

		System.out.println("Before linking up with Neo4j...");
		for (Person person : new Person[] { greg, roy, craig }) {
			System.out.println(person);
		}

		Transaction tx = graphDatabase.beginTx();
		try {
			personRepository.save(greg);
			personRepository.save(roy);
			personRepository.save(craig);

			greg = personRepository.findByName(greg.name);
			greg.worksWith(roy);
			greg.worksWith(craig);
			personRepository.save(greg);

			roy = personRepository.findByName(roy.name);
			roy.worksWith(craig);
			// We already know that roy works with greg
			personRepository.save(roy);

			// We already know craig works with roy and greg

			System.out.println("Lookup each person by name...");
			for (String name : new String[] { greg.name, roy.name, craig.name }) {
				System.out.println(personRepository.findByName(name));
			}

			System.out.println("Looking up who works with Greg...");
			for (Person person : personRepository.findByTeammatesName("Greg")) {
				System.out.println(person.name + " works with Greg.");
			}

			tx.success();
		} finally {
			tx.close();
		}
	}

	public static void main(String[] args) throws IOException {
		FileUtils.deleteRecursively(new File("accessingdataneo4j.db"));
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		App app = (App) context.getBean("app");
		app.work();
	}
}
