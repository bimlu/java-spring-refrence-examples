package com.example.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({
		"com.example.springbatch.config",
		"com.example.springbatch.service",
		"com.example.springbatch.listener",
		"com.example.springbatch.reader",
		"com.example.springbatch.processor",
		"com.example.springbatch.writer",
		"com.example.springbatch.controller"
})
@EnableAsync
@EnableScheduling
public class SpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

}
