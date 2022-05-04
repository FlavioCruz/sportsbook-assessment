package com.coutinho.assessments.sportsbookbusiness.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import com.coutinho.assessments.sportsbookbusiness.service.MatchEventBusiness;
import com.coutinho.assessments.sportsbookkafka.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import static com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.mapper.MatchScoreMapper.INSTANCE;

@Service
public class MatchEventBusinessImpl implements MatchEventBusiness {

    //private final Logger LOGGER = LoggerFactory.getLogger(MatchEventBusinessImpl.class);

    private final KafkaService kafkaService;
    private final MatchScoreService matchScoreService;

    public MatchEventBusinessImpl(KafkaService kafkaService, MatchScoreService matchScoreService) {
        this.kafkaService = kafkaService;
        this.matchScoreService = matchScoreService;
    }

    @Override
    public CompletableFuture<MatchEventDTO> getEvent(String eventName) {
        return matchScoreService
                .getEventByName(eventName)
                .thenApply(INSTANCE::entityToDto);
    }

    @Override
    public CompletableFuture<MatchEventDTO> insertEvent(MatchEventDTO dto, Instant requestReceived) {
        MatchEvent matchEventEntity = INSTANCE.dtoToEntity(dto);
        return matchScoreService
                .createOrUpdateEvent(
                        matchEventEntity,
                        requestReceived
                ).thenApply(INSTANCE::entityToDto);
    }
}
