package com.tabularize.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EntityScan("com.tabularize.app.model")
@ComponentScan(basePackages = "com.tabularize.app")

public class TabularizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TabularizeApplication.class, args);
	}

}
