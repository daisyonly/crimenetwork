package org.crimenetwork.neo4j.demo;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@EnableNeo4jRepositories(basePackages = "org.crimenetwork.neo4j.demo")
@ComponentScan({"org.crimenetwork.neo4j.demo"})
public class ApplicationConfig extends Neo4jConfiguration{
	public ApplicationConfig() {
		setBasePackage("org.crimenetwork.neo4j.demo");
	}

	@Bean
	GraphDatabaseService graphDatabaseService() {
		return new GraphDatabaseFactory().newEmbeddedDatabase("accessingdataneo4j.db");
	}
}
