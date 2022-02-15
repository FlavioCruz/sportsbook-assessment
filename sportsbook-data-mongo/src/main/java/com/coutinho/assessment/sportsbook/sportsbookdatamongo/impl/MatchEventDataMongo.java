package com.coutinho.assessment.sportsbook.sportsbookdatamongo.impl;

import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MatchEventDataMongo implements MatchEventDataSource {

    //private final MongoDatabase database;
    private final ReactiveMongoTemplate template;

    public MatchEventDataMongo(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Flux<MatchEvent> listMatches() {
        return template.findAll(MatchEvent.class);
    }

    @Override
    public Mono<MatchEvent> createOne(MatchEvent matchEvent) {
        return template.save(Mono.just(matchEvent));
    }

    @Override
    public Mono<List<List<MatchEvent>>> createMany(List<MatchEvent> matchEventList) {
        synchronized (template){
            return Flux.just(matchEventList).flatMap(template::insert).collectList();
        }
    }

    @Override
    public MatchEvent updateOne(MatchEvent matchEvent) {
        //template.updateOne(eq("i", 10), new Document("$set", new Document("i", 110)))
        //        .subscribe(new Subscriber<UpdateResult>() {
        //            @Override
        //            public void onSubscribe(Subscription subscription) {
//
        //            }
//
        //            @Override
        //            public void onNext(UpdateResult updateResult) {
//
        //            }
//
        //            @Override
        //            public void onError(Throwable throwable) {
//
        //            }
//
        //            @Override
        //            public void onComplete() {
//
        //            }
        //        });

        return matchEvent;
    }
}
