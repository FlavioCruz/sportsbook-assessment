package com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.Instant;
import java.util.Objects;

@JsonPOJOBuilder
public class MatchEventDTO {

    private String event;
    private String score;

    public MatchEventDTO() {
    }

    private MatchEventDTO(Builder builder) {
        this.event = builder.event;
        this.score = builder.score;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchEventDTO that = (MatchEventDTO) o;
        return Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event);
    }

    @Override
    public String toString() {
        return "MatchEventDTO{" +
                "event='" + event + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public static class Builder{
        private String event;
        private String score;

        public MatchEventDTO.Builder withEvent(String event) {
            this.event = event;
            return this;
        }

        public MatchEventDTO.Builder withScore(String score) {
            this.score = score;
            return this;
        }

        public MatchEventDTO build(){
            return new MatchEventDTO(this);
        }
    }
}
