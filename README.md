# MatchScore API

This api exposes some functionality over a match score.

This application was developed using:
    
  - Java 11
  - Maven
  - Spring
  - MongoDB

And tested using:
  
  - TestNG

## Install

    mvn clean install

## Run the app

    java jar path-to-project/sportsbook-assessment/sportsbook-controller/sportsbook-controller-0.0.1-SNAPSHOT.jar com.coutinho.assessment.sportsbook.sportsbookcontroller.SportsbookApplication 

## Run the tests

    mvn test

# REST API

The REST API to the example app is described below.

## Get a Match

### Request

`GET /matchEventId`

    curl -i -H 'Accept: application/json' http://localhost:8080/Flamengo%20vs%20Fluminense

### Response

    200 OK
   
    { "event": "Flamengo vs Fluminense", "score": "0 - 1" }

## Create a new Thing

### Request

`POST /`

    curl -i -H 'Accept: application/json' -d 'event=Flamengo%20vs%20Fluminense,score=0%20-%201' http://localhost:8080/

### Response

    200 OK

    { "event": "Flamengo vs Fluminense", "score": "0 - 1" }

