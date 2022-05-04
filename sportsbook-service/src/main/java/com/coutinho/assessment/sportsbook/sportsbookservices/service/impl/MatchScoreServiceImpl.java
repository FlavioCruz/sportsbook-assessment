package com.coutinho.assessment.sportsbook.sportsbookservices.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@Service
public class MatchScoreServiceImpl implements MatchScoreService {

    //private final Logger LOGGER = LoggerFactory.getLogger(MatchScoreServiceImpl.class);

    private final MatchEventDataSource dataSource;

    public MatchScoreServiceImpl(MatchEventDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CompletableFuture<MatchEvent> getEventByName(String eventName) {
        return dataSource.getEventByName(eventName);
    }

    @Override
    public CompletableFuture<MatchEvent> createOrUpdateEvent(MatchEvent entity, Instant requestReceived) {
        //LOGGER.info("saving entity {}", entity);
        entity.setRequestReceived(requestReceived);

        CompletableFuture<MatchEvent> result = dataSource.createOrUpdateOne(entity);
        //LOGGER.info("entity saved {}", result);
        return result;
    }
}
