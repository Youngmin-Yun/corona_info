package com.coronainfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CoronainfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronainfoServiceApplication.class, args);
	}

}
