package com.coutinho.assessments.sportsbookkafka.service;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;

public interface KafkaService {

    MatchEvent getMatchEventFromTopic(String topicName);

    String sendMatchEvent(String topicName, MatchEvent matchEvent);
}
