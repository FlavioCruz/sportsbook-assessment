package com.coutinho.assessment.sportsbook.sportsbookservices.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class MatchScoreServiceImplTest {

    @Mock
    private MatchEventDataSource dataSourceMock;
    @InjectMocks
    private MatchScoreService victim;

    private Instant requestReceivedTime;

    @BeforeClass
    public void setUp(){
        dataSourceMock = mock(MatchEventDataSource.class);
        requestReceivedTime = Instant.now();
        victim = new MatchScoreServiceImpl(dataSourceMock);
    }

    @Test(invocationCount = 1, threadPoolSize = 8, dataProvider = "getMatchData")
    public void testGetEventByName_WithSuccess(CompletableFuture<MatchEvent> result, MatchEvent expected)
            throws ExecutionException, InterruptedException {
        when(dataSourceMock.getEventByName(anyString())).thenReturn(result);
        CompletableFuture<MatchEvent> serviceReturn = victim.getEventByName(expected.getId());

        verify(dataSourceMock, atLeast(1)).getEventByName(expected.getId());
        assertEquals(serviceReturn.get(), expected);
    }

    @Test(invocationCount = 1, threadPoolSize = 8, dataProvider = "getMatchData")
    public void testCreateOrUpdateEvent(CompletableFuture<MatchEvent> result, MatchEvent expected)
            throws ExecutionException, InterruptedException {
        when(dataSourceMock.createOrUpdateOne(any(MatchEvent.class))).thenReturn(result);
        Instant creationInstant = Instant.now();
        CompletableFuture<MatchEvent> serviceReturn = victim.createOrUpdateEvent(generateMatchEventsEntity(expected.getId(), expected.getScore()), creationInstant);

        verify(dataSourceMock, atLeast(1)).createOrUpdateOne(result.get());
        assertEquals(serviceReturn.get(), expected);
    }


    @DataProvider(parallel = true, name = "getMatchData")
    private Object[][] futureMatchEventProvider(){
        return new Object[][]{
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 0")),
                        generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 0")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 3")),
                        generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 3")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 2")),
                        generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 2")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 5")),
                        generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 5")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 1")),
                        generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 1")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 7")),
                        generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 7")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 1")),
                        generateMatchEventsEntity("Flamengo vs Fluminense", "0 - 1")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "1 - 0")),
                        generateMatchEventsEntity("Flamengo vs Fluminense", "1 - 0")
                },
                {
                    CompletableFuture.completedFuture(
                            generateMatchEventsEntity("Flamengo vs Fluminense", "1 - 7")),
                        generateMatchEventsEntity("Flamengo vs Fluminense", "1 - 7")
                }
        };
    }

    private MatchEvent generateMatchEventsEntity(String eventName, String eventScore){
        return new MatchEvent.Builder()
                .withId(eventName)
                .withScore(eventScore)
                .withRequestReceived(requestReceivedTime)
                .build();
    }
}