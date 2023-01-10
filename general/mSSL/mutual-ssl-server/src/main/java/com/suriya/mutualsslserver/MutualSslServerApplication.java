package com.suriya.mutualsslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class MutualSslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutualSslServerApplication.class, args);
	}

}
