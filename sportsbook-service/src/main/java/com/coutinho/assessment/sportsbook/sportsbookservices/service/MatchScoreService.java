package com.coutinho.assessment.sportsbook.sportsbookservices.service;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public interface MatchScoreService {

    CompletableFuture<MatchEvent> getEventByName(String eventName);
    CompletableFuture<MatchEvent> createOrUpdateEvent(MatchEvent entity, Instant requestReceived);
}
