package com.coutinho.assessment.sportsbook.sportsbookcontroller.resources;

import com.coutinho.assessment.sportsbook.sportsbookcontroller.controller.MatchScoreController;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class MatchScoreControllerTest {

    @Mock
    private MatchScoreService service;
    private MatchScoreController victim;

    @BeforeClass
    public void setup(){
        service = mock(MatchScoreService.class);
        victim = new MatchScoreController(service);
    }

    @Test(invocationCount = 64, threadPoolSize = 8, dataProvider = "getEventData")
    public void testCreateEvent(CompletableFuture<MatchEventDTO> expected, String param) throws ExecutionException, InterruptedException {
        when(service.getEventByName(anyString())).thenReturn(expected);
        victim.getEvent(param);

        verify(service, atLeast(1)).getEventByName(param);
        assertEquals(expected.get().getEvent(), param);
    }

    @Test(invocationCount = 64, threadPoolSize = 8, dataProvider = "createOrUpdateEventData")
    public void testUpdateEvent(CompletableFuture<MatchEventDTO> expected, MatchEventDTO param) throws ExecutionException, InterruptedException {
        when(service.createOrUpdateEvent(any(MatchEventDTO.class), any(Instant.class))).thenReturn(expected);
        victim.insertEvent(param);

        verify(service, atLeast(1)).createOrUpdateEvent(any(MatchEventDTO.class), any(Instant.class));
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
        MatchEventDTO dto = new MatchEventDTO();
        dto.setEvent(eventName);
        dto.setScore(eventScore);

        return dto;
    }
}