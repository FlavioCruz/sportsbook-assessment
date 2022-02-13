package com.coutinho.assessment.sportsbook.sportsbookcontroller.resources;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.testng.Assert.assertEquals;
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
    public void testCreateEvent(String name, String expected) throws ExecutionException, InterruptedException {
        MatchEventDTO match = new MatchEventDTO();
        match.setEvent(name);
        String result;
        synchronized (service){
            Mockito.when(this.service.createEvent(any(MatchEventDTO.class))).thenReturn(match);
            assertNotNull(this.controller.createEvent(new MatchEventDTO()).get());
            result = this.controller.createEvent(new MatchEventDTO()).get().getEvent();
        }
        assertEquals(result, expected);
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
                {"Ana", "Ana"},
                {"Bruno", "Bruno"},
                {"Caio", "Caio"},
                {"Daniel", "Daniel"},
                {"Ester", "Ester"},
                {"Flavio", "Flavio"},
                {"Gilda", "Gilda"},
                {"Heitor", "Heitor"},
                {"Italo", "Italo"},
                {"Jessica", "Jessica"}
        };
    }
}