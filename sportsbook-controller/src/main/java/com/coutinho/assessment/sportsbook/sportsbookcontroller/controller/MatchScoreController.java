package com.coutinho.assessment.sportsbook.sportsbookcontroller.controller;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookbusiness.service.MatchEventBusiness;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@RestController
public class MatchScoreController {

    private final MatchEventBusiness service;

    public MatchScoreController(MatchEventBusiness service) {
        this.service = service;
    }

    @Async("asyncExecutor")
    @GetMapping(value = "/{eventName}")
    public CompletableFuture<MatchEventDTO> getEvent(@PathVariable(value = "eventName") String eventName){
        return service.getEvent(eventName);
    }

    @PostMapping
    public CompletableFuture<MatchEventDTO> insertEvent(@RequestBody MatchEventDTO dto){
        return service.insertEvent(dto, Instant.now());
    }
}
