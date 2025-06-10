package com.example.feedbackSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class FeedbackSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackSystemApplication.class, args);
		System.out.println("🚀 App started successfully!");

	}

	@PostConstruct
	public void testConsole() {
		System.out.println("✅ Spring Boot Starter Project is Running Successfully!");
	}

}
