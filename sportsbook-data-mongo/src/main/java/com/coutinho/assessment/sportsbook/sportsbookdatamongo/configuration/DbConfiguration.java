package com.coutinho.assessment.sportsbook.sportsbookdatamongo.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@PropertySource("classpath:mongo-database.properties")
@EnableReactiveMongoRepositories
public class DbConfiguration extends AbstractReactiveMongoConfiguration {

    //@Value("mongo.data.server.database")
    @Value("match-event")
    private String databaseName;
    //@Value("mongo.data.server.collection")
    @Value("macthes")
    private String collectionName;

    @Bean(name = "mongoDatabase")
    public MongoClient mongoClient(){
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return this.databaseName;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
    }
}
