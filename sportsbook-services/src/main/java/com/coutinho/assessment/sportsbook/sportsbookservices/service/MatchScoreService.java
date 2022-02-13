package com.coutinho.assessment.sportsbook.sportsbookservices.service;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;

public interface MatchScoreService {

    MatchEventDTO createEvent(MatchEventDTO dto);
    MatchEventDTO updateEvent();
    MatchEventDTO listEvent();
}
