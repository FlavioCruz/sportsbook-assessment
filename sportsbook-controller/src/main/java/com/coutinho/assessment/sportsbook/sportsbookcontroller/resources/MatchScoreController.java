package com.coutinho.assessment.sportsbook.sportsbookcontroller.resources;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class MatchScoreController {

    private final MatchScoreService service;

    public MatchScoreController(MatchScoreService service) {
        this.service = service;
    }

    @Async("asyncExecutor")
    @PostMapping
    public CompletableFuture<MatchEventDTO> createEvent(MatchEventDTO dto){
        return CompletableFuture.completedFuture(service.createEvent(dto));
    }

    @Async("asyncExecutor")
    @PutMapping
    public CompletableFuture<MatchEventDTO> updateEvent(){
        return CompletableFuture.completedFuture(service.updateEvent());
    }

    @Async("asyncExecutor")
    @GetMapping
    public CompletableFuture<MatchEventDTO> listEvent(){
        return CompletableFuture.completedFuture(service.listEvent());
    }
}
