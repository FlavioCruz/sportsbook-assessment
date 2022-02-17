package com.coutinho.assessment.sportsbook.sportsbookservices.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.management.InstanceNotFoundException;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import static com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.mapper.MatchScoreMapper.INSTANCE;

@Service
public class MatchScoreServiceImpl implements MatchScoreService {

    private final Logger LOGGER = LoggerFactory.getLogger(MatchScoreServiceImpl.class);

    private final MatchEventDataSource dataSource;

    public MatchScoreServiceImpl(MatchEventDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CompletableFuture<MatchEventDTO> getEventByName(String eventName) {
        return dataSource.getEventByName(eventName)
                .thenApply(INSTANCE::entityToDto);
    }

    @Override
    public CompletableFuture<MatchEventDTO> createOrUpdateEvent(MatchEventDTO dto, Instant requestReceived) {
        MatchEvent matchEvent = INSTANCE.dtoToEntity(dto);
        LOGGER.info("saving entity {}", matchEvent);
        matchEvent.setRequestReceived(requestReceived);

        CompletableFuture<MatchEvent> result = dataSource.createOrUpdateOne(matchEvent);
        LOGGER.info("entity saved {}", result);
        return result.thenApply(INSTANCE::entityToDto);
    }
}
