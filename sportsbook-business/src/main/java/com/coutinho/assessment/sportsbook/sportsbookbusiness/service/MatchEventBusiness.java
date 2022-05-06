package com.coutinho.assessment.sportsbook.sportsbookbusiness.service;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public interface MatchEventBusiness {

    CompletableFuture<MatchEventDTO>  getEvent(String eventName);

    CompletableFuture<MatchEventDTO> insertEvent(MatchEventDTO dto, Instant requestReceived);
}
