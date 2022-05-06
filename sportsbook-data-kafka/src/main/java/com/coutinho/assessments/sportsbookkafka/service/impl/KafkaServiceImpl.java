package com.coutinho.assessments.sportsbookkafka.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessments.sportsbookkafka.service.KafkaService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaServiceImpl implements KafkaService {

    //private final Logger LOGGER = LoggerFactory.getLogger(KafkaServiceImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @KafkaListener(topics = "mytopic", groupId = "foo")
    public MatchEvent getMatchEventFromTopic(String message) {
        System.out.println("Received Message in group foo: " + message);
        return null;
    }

    @Override
    public ListenableFuture<SendResult<String, String>> sendMatchEvent(String topicName, MatchEvent matchEvent) {

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, matchEvent.toString());

        future.addCallback(getCallback(matchEvent));
        return future;
    }

    private ListenableFutureCallback<SendResult<String, String>> getCallback(MatchEvent matchEvent) {
        return new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + matchEvent.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + matchEvent.getId() + "] due to : " + ex.getMessage());
            }
        };
    }
}
