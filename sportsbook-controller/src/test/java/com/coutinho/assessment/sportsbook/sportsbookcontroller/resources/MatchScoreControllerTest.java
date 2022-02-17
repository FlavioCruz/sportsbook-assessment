package com.coutinho.assessment.sportsbook.sportsbookcontroller.resources;

import com.coutinho.assessment.sportsbook.sportsbookcontroller.controller.MatchScoreController;
import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.testng.Assert.assertNotNull;

public class MatchScoreControllerTest {

    @Mock
    private MatchScoreService service;
    private MatchScoreController controller;

    @BeforeMethod
    public void setUp() {
        this.service = Mockito.mock(MatchScoreService.class);
        this.controller = new MatchScoreController(this.service);
    }

    @Test(invocationCount = 64, threadPoolSize = 8, dataProvider = "randomData")
    public void testCreateEvent(MatchEventDTO matchEventDTO, String expected) throws ExecutionException, InterruptedException {

        CompletableFuture<MatchEventDTO> future = CompletableFuture.completedFuture(matchEventDTO);

        synchronized (service) {
            Mockito.when(this.service.createOrUpdateEvent(any(MatchEventDTO.class), any(Instant.class))).thenReturn(future);

            assertNotNull(this.controller.insertEvent(new MatchEventDTO()).get());
        }
    }

    @Test
    public void testUpdateEvent() {
    }

    @Test
    public void testListEvent() {
    }


    @DataProvider(parallel = true)
    public Object[][] randomData(){
        return new Object[][]{
                {generateMatchEventsDto("Flamengo vs Fluminense", "0 - 0"), "0 - 0"},
                {generateMatchEventsDto("Flamengo vs Fluminense", "0 - 3"), "0 - 3"},
                {generateMatchEventsDto("Flamengo vs Fluminense", "0 - 2"), "0 - 3"},
                {generateMatchEventsDto("Flamengo vs Fluminense", "0 - 5"), "Daniel"},
                {generateMatchEventsDto("Flamengo vs Fluminense", "0 - 1"), "Ester"},
                {generateMatchEventsDto("Flamengo vs Fluminense", "0 - 7"), "Flavio"},
                {generateMatchEventsDto("Flamengo vs Fluminense", "0 - 1"), "Gilda"},
                {generateMatchEventsDto("Flamengo vs Fluminense", "1 - 0"), "Heitor"},
                {generateMatchEventsDto("Flamengo vs Fluminense", "1 - 7"), "Italo"},
                {generateMatchEventsDto("Flamengo vs Fluminense", "0 - 0"), "Jessica"}
        };
    }

    private MatchEventDTO generateMatchEventsDto(String eventName, String eventScore){
        MatchEventDTO dto = new MatchEventDTO();
        dto.setEvent(eventName);
        dto.setScore(eventScore);

        return dto;
    }
}