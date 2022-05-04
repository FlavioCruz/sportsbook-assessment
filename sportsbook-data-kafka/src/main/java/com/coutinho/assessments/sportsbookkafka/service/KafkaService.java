package com.coutinho.assessments.sportsbookkafka.service;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public interface KafkaService {

    MatchEvent getMatchEventFromTopic(String topicName);

    ListenableFuture<SendResult<String, String>> sendMatchEvent(String topicName, MatchEvent matchEvent);
}
