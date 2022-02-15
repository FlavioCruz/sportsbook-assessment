package com.coutinho.assessment.sportsbook.sportsbookcontroller;

import com.coutinho.assessment.sportsbook.sportsbookservices.service.MatchScoreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ScopeMetadata;

@SpringBootApplication
@ComponentScan(value = "com.coutinho.assessment.sportsbook.*")
public class SportsbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsbookApplication.class, args);
	}

}
