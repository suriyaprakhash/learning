package com.suriya.mutualsslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MutualSslServerApplication {

	public static void main(String[] args) {
//		System.setProperty("javax.net.ssl.keyStore", "C:\\Suriya\\ws\\learning\\general\\mSSL\\mutual-ssl-server\\src\\main\\resources\\certs\\server-keystore.jks");
//		System.setProperty("javax.net.ssl.keyStorePassword", "password");
//		System.setProperty("javax.net.ssl.keyStoreType", "jks");
//		System.setProperty("javax.net.ssl.trustStore", "C:\\Suriya\\ws\\learning\\general\\mSSL\\mutual-ssl-server\\src\\main\\resources\\certs\\server-truststore.jks");
//		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		SpringApplication.run(MutualSslServerApplication.class, args);
	}

}
