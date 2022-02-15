package com.coutinho.assessment.sportsbook.sportsbookservices.service;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;

import java.util.List;

public interface MatchScoreService {

    MatchEventDTO createEvent(MatchEventDTO dto);
    MatchEventDTO updateEvent();
    List<MatchEventDTO> listEvents();
}
