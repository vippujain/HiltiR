package com.hilti.recommendation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class HiltiRecommendationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiltiRecommendationApplication.class, args);
	}
}
