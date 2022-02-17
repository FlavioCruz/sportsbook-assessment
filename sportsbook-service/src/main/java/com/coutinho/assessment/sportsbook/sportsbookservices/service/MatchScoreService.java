package com.coutinho.assessment.sportsbook.sportsbookservices.service;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public interface MatchScoreService {

    CompletableFuture<MatchEventDTO> getEventByName(String eventName);
    CompletableFuture<MatchEventDTO> createOrUpdateEvent(MatchEventDTO dto, Instant requestReceived);
}
