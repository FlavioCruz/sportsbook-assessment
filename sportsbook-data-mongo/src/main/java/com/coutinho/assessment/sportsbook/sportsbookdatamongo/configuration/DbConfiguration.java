package com.coutinho.assessment.sportsbook.sportsbookdatamongo.configuration;

import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class DbConfiguration {

    @Value("mongo.data.server.database")
    private String databaseName;
    @Value("mongo.data.server.collection")
    private String collectionName;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> matchEventMongoCollection;

    @Bean(name = "mongoCollection")
    public MongoCollection<Document> getMongoCollection(){
        matchEventMongoCollection = MongoClients.create().getDatabase(databaseName).getCollection(collectionName);
        return matchEventMongoCollection;
    }
}
