package com.coutinho.assessment.sportsbook.sportsbookmodel.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;

@Document(collection = "match")
@JsonPOJOBuilder
@JsonSerialize
public class MatchEvent {

    @Id
    private String id;
    private String score;
    private Instant requestReceived;

    public MatchEvent() {
    }

    private MatchEvent(Builder builder) {
        this.id = builder.id;
        this.score = builder.score;
        this.requestReceived = builder.requestReceived;
    }

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

    public static class Builder{
        private String id;
        private String score;
        private Instant requestReceived;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withScore(String score) {
            this.score = score;
            return this;
        }

        public Builder withRequestReceived(Instant requestReceived) {
            this.requestReceived = requestReceived;
            return this;
        }

        public MatchEvent build(){
            return new MatchEvent(this);
        }
    }
}
