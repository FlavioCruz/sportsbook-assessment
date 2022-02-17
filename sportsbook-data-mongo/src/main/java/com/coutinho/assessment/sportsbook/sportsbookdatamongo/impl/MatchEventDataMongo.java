package com.coutinho.assessment.sportsbook.sportsbookdatamongo.impl;

import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MatchEventDataMongo implements MatchEventDataSource {

    private final ReactiveMongoTemplate template;

    public MatchEventDataMongo(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public CompletableFuture<MatchEvent> getEventByName(String entityId) {
        Mono<MatchEvent> matchResult = template.findById(entityId, MatchEvent.class);
        return matchResult.toFuture();
    }

    @Override
    public CompletableFuture<List<MatchEvent>> listMatches() {
        return template.findAll(MatchEvent.class).collectList().toFuture();
    }

    @Override
    public CompletableFuture<MatchEvent> createOrUpdateOne(MatchEvent matchEvent) {
        MatchEvent currEvent = template.findById(matchEvent.getId(), MatchEvent.class)
                .blockOptional().orElse(null);

        if(currEvent == null || currEvent.getRequestReceived().isBefore(matchEvent.getRequestReceived())){
            return template.save(Mono.just(matchEvent)).toFuture();
        }
        return CompletableFuture.completedFuture(currEvent);
    }
}
