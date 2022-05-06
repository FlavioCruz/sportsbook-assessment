package com.coutinho.assessment.sportsbook.sportsbookbusiness.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookbusiness.service.MatchEventBusiness;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.mapper.MatchScoreMapper;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import com.coutinho.assessments.sportsbookkafka.service.KafkaService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@Service
public class MatchEventBusinessImpl implements MatchEventBusiness {

    //private final Logger LOGGER = LoggerFactory.getLogger(MatchEventBusinessImpl.class);

    private final KafkaService kafkaService;
    private final MatchScoreService matchScoreService;
    private final MatchScoreMapper mapper;

    public MatchEventBusinessImpl(KafkaService kafkaService, MatchScoreService matchScoreService, MatchScoreMapper mapper) {
        this.kafkaService = kafkaService;
        this.matchScoreService = matchScoreService;
        this.mapper = mapper;
    }

    @Override
    public CompletableFuture<MatchEventDTO> getEvent(String eventName) {
        return matchScoreService
                .getEventByName(eventName)
                .thenApply(mapper::entityToDto);
    }

    @Override
    public CompletableFuture<MatchEventDTO> insertEvent(MatchEventDTO dto, Instant requestReceived) {
        MatchEvent matchEventEntity = mapper.dtoToEntity(dto);

        return matchScoreService
                .createOrUpdateEvent(
                        matchEventEntity,
                        requestReceived)
                .thenApply(mapper::entityToDto)
                .whenComplete(
                        (matchEventDTO, throwable) -> kafkaService
                                .sendMatchEvent("mytopic", mapper.dtoToEntity(matchEventDTO)));
    }
}
