package com.coutinho.assessment.sportsbook.sportsbookservices.service;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;

import java.time.Instant;
import java.util.List;

public interface MatchScoreService {

    MatchEventDTO getEventByName(String eventName) throws Exception;
    MatchEventDTO createOrUpdateEvent(MatchEventDTO dto, Instant requestReceived);
    List<MatchEventDTO> listEvents();
}
