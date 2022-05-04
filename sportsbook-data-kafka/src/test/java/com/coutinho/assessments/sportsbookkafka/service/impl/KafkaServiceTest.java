package com.coutinho.assessments.sportsbookkafka.service.impl;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.coutinho.assessments.sportsbookkafka.configuration.KafkaTopicConfig;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KafkaServiceTest {

    private Properties properties;
    private Admin admin;
    KafkaServiceImpl victim;
    private final String bootstrapAddress = "localhost:9092";
    private KafkaTopicConfig kafkaTopicConfig;

    @BeforeEach
    public void setUp(){
        properties = new Properties();
        properties.put(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress
        );

        admin = Admin.create(properties);
        kafkaTopicConfig = mock(KafkaTopicConfig.class);
        when(kafkaTopicConfig.kafkaAdmin()).thenReturn(kafkaAdmin());
        victim = new KafkaServiceImpl(kafkaTemplate());
    }

    @Test
    void givenTopicName_whenCreateNewTopic_thenTopicIsCreated() throws Exception {
        admin.createTopics(Collections.singletonList(new NewTopic("testTopic", 1, (short) 1)));
        admin.listTopics().names().get().forEach(System.out::println);
        assertTrue(admin.listTopics().names().get().contains("testTopic"));
    }

    @Test
    void sendMessage(){
        victim.sendMatchEvent(
            "mytopic",
                new MatchEvent.Builder().withId("Game 1").withScore("Score 1").build()
        ).completable().join();
    }

    private ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    private  KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    private KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

}