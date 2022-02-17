package com.coutinho.assessment.sportsbook.sportsbookservices.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class MatchScoreServiceImplTest {

    @Mock
    private MatchEventDataSource dataSourceMock;
    @InjectMocks
    private MatchScoreService victim;

    @BeforeClass
    public void setUp(){
        dataSourceMock = mock(MatchEventDataSource.class);
        victim = new MatchScoreServiceImpl(dataSourceMock);
    }

    @Test(invocationCount = 64, threadPoolSize = 8, dataProvider = "getMatchData")
    public void testGetEventByName_WithSuccess(CompletableFuture<MatchEvent> result, MatchEventDTO expected)
            throws ExecutionException, InterruptedException {
        when(dataSourceMock.getEventByName(anyString())).thenReturn(result);
        CompletableFuture<MatchEventDTO> serviceReturn = victim.getEventByName(expected.getEvent());

        verify(dataSourceMock, atLeast(1)).getEventByName(expected.getEvent());
        assertEquals(serviceReturn.get(), expected);
    }

    @Test(invocationCount = 64, threadPoolSize = 8, dataProvider = "getMatchData")
    public void testCreateOrUpdateEvent(CompletableFuture<MatchEvent> result, MatchEventDTO expected)
            throws ExecutionException, InterruptedException {
        when(dataSourceMock.createOrUpdateOne(any(MatchEvent.class))).thenReturn(result);
        Instant creationInstant = Instant.now();
        CompletableFuture<MatchEventDTO> serviceReturn = victim.createOrUpdateEvent(generateMatchEventsDto(expected.getEvent(), expected.getScore()), creationInstant);

        verify(dataSourceMock, atLeast(1)).createOrUpdateOne(generateMatchEventsEntity(expected.getEvent(), expected.getScore()));
        assertEquals(serviceReturn.get(), expected);
    }


    @DataProvider(name = "getMatchData")
    private Object[][] futureMatchEventProvider(){
        return new Object[][]{
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 0")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 0")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 3")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 3")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 2")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 2")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 5")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 5")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 1")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 1")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 7")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 7")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 1")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "0 - 1")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "1 - 0")),
                        generateMatchEventsDto("Flamengo vs Fluminense", "1 - 0")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "1 - 7")),
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

    private MatchEvent generateMatchEventsEntity(String eventName, String eventScore){
        MatchEvent entity = new MatchEvent();
        entity.setId(eventName);
        entity.setScore(eventScore);

        return entity;
    }
}