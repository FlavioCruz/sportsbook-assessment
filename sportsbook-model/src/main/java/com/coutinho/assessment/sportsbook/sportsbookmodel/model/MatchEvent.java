package com.coutinho.assessment.sportsbook.sportsbookmodel.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@JsonPOJOBuilder
public class MatchEvent {

    @Id
    private String id;
    private String score;
    private Instant requestReceived;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Instant getRequestReceived() {
        return requestReceived;
    }

    public void setRequestReceived(Instant requestReceived) {
        this.requestReceived = requestReceived;
    }
}
