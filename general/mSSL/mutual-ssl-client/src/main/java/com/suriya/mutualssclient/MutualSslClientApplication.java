package com.suriya.mutualssclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.net.http.HttpClient;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class MutualSslClientApplication {

//	@Bean
//	public WebClient webClient() throws SSLException, NoSuchAlgorithmException {
//		javax.net.ssl.SSLContext sslContext = SSLContext.getDefault();
//		HttpClient httpClient = HttpClient.newBuilder().sslContext(sslContext).build();
//		return WebClient.builder().baseUrl("https://localhost:8082").clientConnector(new JdkClientHttpConnector(httpClient)).build();
//	}


	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore", "C:\\Suriya\\ws\\learning\\general\\mSSL\\mutual-ssl-client\\src\\main\\resources\\certs\\client-truststore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		System.setProperty("javax.net.debug", "ssl:handshake");
		SpringApplication.run(MutualSslClientApplication.class, args);
	}

}
