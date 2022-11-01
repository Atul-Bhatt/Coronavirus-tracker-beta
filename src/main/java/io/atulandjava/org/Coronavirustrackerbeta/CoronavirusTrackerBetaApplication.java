package io.atulandjava.org.Coronavirustrackerbeta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronavirusTrackerBetaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusTrackerBetaApplication.class, args);
	}

}
