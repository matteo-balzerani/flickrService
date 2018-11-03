package it.mb.service.flickr.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "it.mb.service.flickr.repository")
public class MongoDbConfig {

}
