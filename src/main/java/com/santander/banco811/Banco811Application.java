package com.santander.banco811;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class Banco811Application {

	public static void main(String[] args) {
		SpringApplication.run(Banco811Application.class, args);
	}

}
