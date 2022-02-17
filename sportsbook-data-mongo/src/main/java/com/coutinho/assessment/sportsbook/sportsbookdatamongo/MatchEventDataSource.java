package com.coutinho.assessment.sportsbook.sportsbookdatamongo;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MatchEventDataSource {

    CompletableFuture<MatchEvent> getEventByName(String entityId);
    CompletableFuture<List<MatchEvent>> listMatches();

    CompletableFuture<MatchEvent> createOrUpdateOne(MatchEvent matchEvent);
}
