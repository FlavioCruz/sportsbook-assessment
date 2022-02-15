package com.coutinho.assessment.sportsbook.sportsbookdatamongo;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MatchEventDataSource {

    Flux <MatchEvent> listMatches();

    Mono<MatchEvent> createOne(MatchEvent matchEvent);

    Mono<List<List<MatchEvent>>> createMany(List<MatchEvent> matchEventList);

    MatchEvent updateOne(MatchEvent matchEvent);
}
