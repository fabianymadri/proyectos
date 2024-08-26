package com.lucasian.pedirapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("com.lucasian.pedirapi.repository")
@EntityScan(basePackages = "com.lucasian.pedirapi.entity")
public class PedirapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedirapiApplication.class, args);
	}

}
