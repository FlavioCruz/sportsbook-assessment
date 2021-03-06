package com.coutinho.assessment.sportsbook.sportsbookcontroller.resources;

import com.coutinho.assessment.sportsbook.sportsbookcontroller.controller.MatchScoreController;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookbusiness.service.MatchEventBusiness;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class MatchScoreControllerTest {

    @Mock
    private MatchEventBusiness service;
    private MatchScoreController victim;

    @BeforeClass
    public void setup(){
        service = mock(MatchEventBusiness.class);
        victim = new MatchScoreController(service);
    }

    @Test(invocationCount = 64, threadPoolSize = 4, dataProvider = "getEventData")
    public void testCreateEvent(CompletableFuture<MatchEventDTO> expected, String param) throws ExecutionException, InterruptedException {
        when(service.getEvent(anyString())).thenReturn(expected);
        victim.getEvent(param);

        verify(service, atLeast(1)).getEvent(param);
        assertEquals(expected.get().getEvent(), param);
    }

    @Test(invocationCount = 64, threadPoolSize = 4, dataProvider = "createOrUpdateEventData")
    public void testUpdateEvent(CompletableFuture<MatchEventDTO> expected, MatchEventDTO param) throws ExecutionException, InterruptedException {
        when(service.insertEvent(any(MatchEventDTO.class), any(Instant.class))).thenReturn(expected);
        victim.insertEvent(param);

        verify(service, atLeast(1)).insertEvent(any(MatchEventDTO.class), any(Instant.class));
        assertEquals(expected.get(), param);
    }


    @DataProvider(parallel = true, name = "getEventData")
    public Object[][] getEventData(){
        return new Object[][]{
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 0")),
                        "Flamengo vs Fluminense"
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 3")),
                        "Flamengo vs Fluminense"
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 2")),
                        "Flamengo vs Fluminense"
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 5")),
                        "Flamengo vs Fluminense"
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 1")),
                        "Flamengo vs Fluminense"
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 7")),
                        "Flamengo vs Fluminense"
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 1")),
                        "Flamengo vs Fluminense"
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "1 - 0")),
                        "Flamengo vs Fluminense"
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "1 - 7")),
                        "Flamengo vs Fluminense"
                }
        };
    }

    @DataProvider(parallel = true, name = "createOrUpdateEventData")
    public Object[][] createOrUpdateEventData(){
        return new Object[][]{
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 0")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 0")
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 3")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 3")
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 2")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 2")
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 5")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 5")
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 1")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 1")
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 7")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 7")
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "0 - 1")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 1")
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "1 - 0")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "1 - 0")
                },
                {
                        CompletableFuture.completedFuture(
                                generateMatchEventsDto("Flamengo vs Fluminense", "1 - 7")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "1 - 7")
                }
        };
    }

    private MatchEventDTO generateMatchEventsDto(String eventName, String eventScore){
        return new MatchEventDTO.Builder()
                .withEvent(eventName)
                .withScore(eventScore)
                .build();
    }
}