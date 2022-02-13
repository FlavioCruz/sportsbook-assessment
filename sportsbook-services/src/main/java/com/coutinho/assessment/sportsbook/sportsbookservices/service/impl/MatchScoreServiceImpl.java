package com.coutinho.assessment.sportsbook.sportsbookservices.service.impl;


import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.mapper.MatchScoreMapper;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.springframework.stereotype.Service;

@Service
public class MatchScoreServiceImpl implements MatchScoreService {

    private final MatchEventDataSource dataSource;

    public MatchScoreServiceImpl(MatchEventDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public MatchEventDTO createEvent(MatchEventDTO dto) {
        dataSource.createOne(MatchScoreMapper.INSTANCE.dtoToEntity(dto));

        return new MatchEventDTO();
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
