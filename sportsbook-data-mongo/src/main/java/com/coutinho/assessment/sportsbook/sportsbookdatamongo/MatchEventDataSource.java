package com.coutinho.assessment.sportsbook.sportsbookdatamongo;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import org.reactivestreams.Publisher;

import java.util.List;

public interface MatchEventDataSource {

    Publisher<InsertOneResult> createOne(MatchEvent matchEvent);

    Publisher<InsertManyResult> createMany(List<MatchEvent> matchEventList);

    MatchEvent updateOne(MatchEvent matchEvent);
}
