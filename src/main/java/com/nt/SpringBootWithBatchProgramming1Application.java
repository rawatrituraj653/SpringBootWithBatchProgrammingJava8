package com.nt;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBootWithBatchProgramming1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithBatchProgramming1Application.class, args);
	}

}
