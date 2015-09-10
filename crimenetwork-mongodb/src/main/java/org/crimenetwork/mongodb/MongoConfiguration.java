package org.crimenetwork.mongodb;

import com.mongodb.Mongo;

import org.crimenetwork.mongodb.utils.MongoDbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
@Configuration
@EnableMongoRepositories
@PropertySource("classpath:mongodb.properties")
public class MongoConfiguration extends AbstractMongoConfiguration {
	@Autowired
	Environment env;
	
    @Override
    protected String getDatabaseName() {
        return env.getProperty("dbName");
    }
    @Override
    public Mongo mongo() throws Exception {
    	return MongoDbUtil.getMongo();

    }
    @Override
    protected String getMappingBasePackage() {
        return "com.vmojing.mongodb.domain";
    }
    /**
     * can remove _class
     */
	@SuppressWarnings("deprecation")
	public @Bean MongoTemplate mongoTemplate() throws Exception {
		// remove _class
		MappingMongoConverter converter = new MappingMongoConverter(
				mongoDbFactory(), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(),
				converter);
		return mongoTemplate;

	}
	
}
