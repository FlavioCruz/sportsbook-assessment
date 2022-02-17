package com.coutinho.assessment.sportsbook.sportsbookservices.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookdatamongo.MatchEventDataSource;
import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

public class MatchScoreServiceImplTest {

    @Mock
    private MatchEventDataSource dataSourceMock;
    private MatchScoreService victim;

    @BeforeMethod
    public void setUp(){
        dataSourceMock = mock(MatchEventDataSource.class);
        victim = new MatchScoreServiceImpl(dataSourceMock);
    }

    @Test
    public void testGetEventByName_WithSuccess() {
    }

    @Test
    public void testGetEventByName_WithFailure() {
    }

    @Test
    public void testCreateOrUpdateEvent() {
    }

    @Test
    public void testListEvents() {
    }
}