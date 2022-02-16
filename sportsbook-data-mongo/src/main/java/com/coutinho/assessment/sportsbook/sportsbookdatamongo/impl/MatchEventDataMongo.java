package com.coutinho.assessment.sportsbook.sportsbookdatamongo.impl;

import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MatchEventDataMongo implements MatchEventDataSource {

    //private final MongoDatabase database;
    private final ReactiveMongoTemplate template;

    public MatchEventDataMongo(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Mono<MatchEvent> getEventByName(String entityId) {
        return template.findById(entityId, MatchEvent.class);
    }

    @Override
    public Flux<MatchEvent> listMatches() {
        return template.findAll(MatchEvent.class);
    }

    @Override
    public Mono<MatchEvent> createOrUpdateOne(MatchEvent matchEvent) {
        MatchEvent currEvent = template.findById(matchEvent.getId(), MatchEvent.class)
                .blockOptional().orElse(null);

        if(currEvent == null || currEvent.getRequestReceived().isBefore(matchEvent.getRequestReceived())){
            return template.save(Mono.just(matchEvent));
        }
        return Mono.just(currEvent);
    }
}
