package com.coutinho.assessment.sportsbook.sportsbookcontroller.controller;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@RestController
public class MatchScoreController {

    private final Logger LOGGER = LoggerFactory.getLogger(MatchScoreController.class);

    private final MatchScoreService service;

    public MatchScoreController(MatchScoreService service) {
        this.service = service;
    }

    @Async("asyncExecutor")
    @GetMapping(value = "/{eventName}")
    public CompletableFuture<MatchEventDTO> getEvent(@PathVariable(value = "eventName") String eventName){
        LOGGER.info("operation=getEvent, param={}", eventName);
        return service.getEventByName(eventName);
    }

    @PostMapping
    public CompletableFuture<MatchEventDTO> insertEvent(@RequestBody MatchEventDTO dto){
        LOGGER.info("operation=insertEvent started");
        return service.createOrUpdateEvent(dto, Instant.now());
    }
}
