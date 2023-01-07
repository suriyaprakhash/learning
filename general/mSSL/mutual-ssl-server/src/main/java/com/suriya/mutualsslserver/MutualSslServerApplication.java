package com.suriya.mutualsslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MutualSslServerApplication {

	public static void main(String[] args) {
		System.setProperty("javax.net.debug", "ssl:handshake");
		System.setProperty("javax.net.ssl.trustStore", "server.ssl.trust-store");
		System.setProperty("javax.net.ssl.trustStorePassword", "server.ssl.trust-store-password");
//		System.setProperty("javax.net.ssl.trustStore", "C:\\Suriya\\ws\\learning\\general\\mSSL\\mutual-ssl-server\\mutual-ssl-server\\src\\main\\resources\\certs\\ca-trust");
		SpringApplication.run(MutualSslServerApplication.class, args);
	}

}
