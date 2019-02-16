package com.toni.ferreiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SisfactuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisfactuApplication.class, args);
	}

}

