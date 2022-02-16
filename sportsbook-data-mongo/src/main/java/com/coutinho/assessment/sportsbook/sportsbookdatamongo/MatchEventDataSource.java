package com.coutinho.assessment.sportsbook.sportsbookdatamongo;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MatchEventDataSource {

    Mono<MatchEvent> getEventByName(String entityId);
    Flux <MatchEvent> listMatches();

    Mono<MatchEvent> createOrUpdateOne(MatchEvent matchEvent);
}
