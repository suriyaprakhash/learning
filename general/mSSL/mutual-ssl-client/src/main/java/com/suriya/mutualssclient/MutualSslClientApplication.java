package com.suriya.mutualssclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MutualSslClientApplication {
	public static void main(String[] args) {
//		System.setProperty("javax.net.ssl.keyStore", "C:\\Suriya\\ws\\learning\\general\\mSSL\\mutual-ssl-client\\src\\main\\resources\\certs\\client-keystore.jks");
//		System.setProperty("javax.net.ssl.keyStorePassword", "password");

//		The truststore in the code provides trust store info of the server during one way SSL
//		System.setProperty("javax.net.ssl.trustStore", "C:\\Suriya\\ws\\learning\\general\\mSSL\\mutual-ssl-client\\src\\main\\resources\\certs\\client-truststore.jks");

		SpringApplication.run(MutualSslClientApplication.class, args);
	}





}
