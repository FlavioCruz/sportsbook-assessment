package com.coutinho.assessment.sportsbook.sportsbookservices.service.impl;


import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.springframework.stereotype.Service;

@Service
public class MatchScoreServiceImpl implements MatchScoreService {

    @Override
    public MatchEventDTO createEvent(String name) {
        MatchEventDTO match = new MatchEventDTO();
        match.setEvent(name);

        return match;
    }

    @Override
    public MatchEventDTO updateEvent() {
        return null;
    }

    @Override
    public MatchEventDTO listEvent() {
        return null;
    }
}
