package com.coutinho.assessment.sportsbook.sportsbookdatamongo.impl;

import com.coutinho.assessment.sportsbook.mapper.EntityToDocumentMapper;
import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public class MatchEventDataMongo implements MatchEventDataSource {

    //private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public MatchEventDataMongo(MongoCollection collection) {
        this.collection = collection;
    }

    @Override
    public Publisher<InsertOneResult> createOne(MatchEvent matchEvent) {
        return collection.insertOne(EntityToDocumentMapper.INSTANCE.entityToDocument(matchEvent));
    }

    @Override
    public Publisher<InsertManyResult> createMany(List<MatchEvent> matchEventList) {
        return collection.insertMany(null);
    }

    @Override
    public MatchEvent updateOne(MatchEvent matchEvent) {
        collection.updateOne(eq("i", 10), new Document("$set", new Document("i", 110)))
                .subscribe(new Subscriber<UpdateResult>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {

                    }

                    @Override
                    public void onNext(UpdateResult updateResult) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return matchEvent;
    }
}
