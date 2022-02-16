package com.coutinho.assessment.sportsbook.sportsbookservices.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.mapper.MatchScoreMapper;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchScoreServiceImpl implements MatchScoreService {

    private final MatchEventDataSource dataSource;

    public MatchScoreServiceImpl(MatchEventDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public MatchEventDTO getEventByName(String eventName) {
        MatchEvent resultEntity = dataSource.getEventByName(eventName).blockOptional().orElseThrow();
        return MatchScoreMapper.INSTANCE.entityToDto(resultEntity);
    }

    @Override
    public MatchEventDTO createOrUpdateEvent(MatchEventDTO dto, Instant requestReceived) {

        MatchEvent matchEvent = MatchScoreMapper.INSTANCE.dtoToEntity(dto);
        matchEvent.setRequestReceived(requestReceived);
        MatchEvent insertResult = dataSource.createOrUpdateOne(matchEvent).blockOptional().orElseThrow();
        return MatchScoreMapper.INSTANCE.entityToDto(insertResult);
    }

    @Override
    public List<MatchEventDTO> listEvents() {
        Flux<MatchEvent> eventFlux = dataSource.listMatches();
        List<MatchEvent> listEntity = eventFlux
                .collectList()
                .blockOptional()
                .orElse(null);

        assert listEntity != null;
        return listEntity.stream().map(MatchScoreMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }
}
