package com.coutinho.assessment.sportsbook.sportsbookcontroller.resources;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class MatchScoreController {

    private final MatchScoreService service;

    public MatchScoreController(MatchScoreService service) {
        this.service = service;
    }

    @Async("asyncExecutor")
    @GetMapping
    public CompletableFuture<List<MatchEventDTO>> listEvent(String eventName){
        return CompletableFuture.completedFuture(service.listEvents());
    }

    @PostMapping
    public CompletableFuture<MatchEventDTO> insertEvent(@RequestBody MatchEventDTO dto){
        Instant requestReceived = Instant.now();
        return CompletableFuture.completedFuture(service.createOrUpdateEvent(dto, requestReceived));
    }

    @Async("asyncExecutor")
    @PostMapping(value = "/testing")
    public CompletableFuture<Collection<MatchEventDTO>> insertEvent(@RequestBody List<MatchEventDTO> dtos){
        dtos.forEach(x -> service.createOrUpdateEvent(x, Instant.now()));

        return CompletableFuture.completedFuture(
                new HashSet<>(
                        dtos.stream().map(x -> {
                            try {
                                return service.getEventByName(x.getEvent());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).collect(Collectors.toList())
                )
        );
    }
}
