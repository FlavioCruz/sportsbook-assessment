package com.coutinho.assessments.sportsbookkafka.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessments.sportsbookkafka.configuration.KafkaTopicConfig;
import com.coutinho.assessments.sportsbookkafka.service.KafkaService;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;

    public KafkaServiceImpl(KafkaTemplate<String, String> kafkaTemplate, ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaListenerContainerFactory = kafkaListenerContainerFactory;

    }

    @Override
    public MatchEvent getMatchEventFromTopic(String topicName) {

        //kafkaTemplate.receive()

        return null;
    }

    @Override
    public String sendMatchEvent(String topicName, MatchEvent matchEvent) {
        try{

            ListenableFuture<SendResult<String, String>> future =
                    kafkaTemplate.send(topicName, matchEvent.toString());

            future.addCallback(new ListenableFutureCallback<>() {

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
            });
            return matchEvent.getId();
        } catch (KafkaException e){

            kafkaListenerContainerFactory.getConsumerFactory()-


            kafkaTopicConfig.kafkaAdmin().createOrModifyTopics(kafkaTopicConfig.topic1());
            return sendMatchEvent(topicName, matchEvent);
        }
    }
}
