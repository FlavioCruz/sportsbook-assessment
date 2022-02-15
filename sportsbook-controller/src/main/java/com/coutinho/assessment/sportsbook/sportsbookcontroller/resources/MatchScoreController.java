package com.coutinho.assessment.sportsbook.sportsbookcontroller.resources;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class MatchScoreController {

    private final MatchScoreService service;

    public MatchScoreController(MatchScoreService service) {
        this.service = service;
    }

    @Async("asyncExecutor")
    @GetMapping
    public CompletableFuture<List<MatchEventDTO>> listEvent(){
        return CompletableFuture.completedFuture(service.listEvents());
    }

    @PostMapping
    public CompletableFuture<MatchEventDTO> createEvent(@RequestBody MatchEventDTO dto){
        return CompletableFuture.completedFuture(service.createEvent(dto));
    }

    @PutMapping
    public CompletableFuture<MatchEventDTO> updateEvent(){
        return CompletableFuture.completedFuture(service.updateEvent());
    }
}
