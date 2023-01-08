package com.suriya.mutualssclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;

import java.net.http.HttpClient;
import java.security.KeyStore;

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
