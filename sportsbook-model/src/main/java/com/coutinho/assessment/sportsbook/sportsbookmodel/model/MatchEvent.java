package com.coutinho.assessment.sportsbook.sportsbookmodel.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchEvent event = (MatchEvent) o;
        return Objects.equals(id, event.id) && Objects.equals(score, event.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score);
    }

    @Override
    public String toString() {
        return "MatchEvent{" +
                "id='" + id + '\'' +
                ", score='" + score + '\'' +
                ", requestReceived=" + requestReceived +
                '}';
    }
}
