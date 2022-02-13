package com.coutinho.assessment.sportsbook.sportsbookservices.service;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;

public interface MatchScoreService {

    MatchEventDTO createEvent(String name);
    MatchEventDTO updateEvent();
    MatchEventDTO listEvent();
}
