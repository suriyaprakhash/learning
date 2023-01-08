package com.suriya.mutualssclient;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
//
//import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.ssl.SSLContexts;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SystemPropertySetter {

   @Value("${suriya.client.ssl.trust-store}")
   private String trustStorePath;

   @Value("${suriya.client.ssl.trust-store-password}")
   private String trustStorePassword;
//
//   @Value("${suriya.client.ssl.key-store}")
//   private String keyStorePath;
//
//   @Value("${suriya.client.ssl.key-store-password}")
//   private String keyStorePassword;

   @PostConstruct
   public void setProperty() {
//      System.setProperty("javax.net.ssl.trustStore", "C:\\Suriya\\ws\\learning\\general\\mSSL\\mutual-ssl-client\\src\\main\\resources\\certs\\client-truststore.jks");
//      System.setProperty("javax.net.ssl.trustStorePassword", "password");
      System.setProperty("javax.net.ssl.trustStore", trustStorePath);
      System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
      System.setProperty("javax.net.debug", "ssl:handshake");
      System.setProperty("javax.net.debug", "all");
   }

//
//   public SSLContext getTwoWaySslContext() {
//
//      try(FileInputStream keyStoreFileInputStream = new FileInputStream(ResourceUtils.getFile(keyStorePath));
//          FileInputStream trustStoreFileInputStream = new FileInputStream(ResourceUtils.getFile(trustStorePath));
//      ) {
//         KeyStore keyStore = KeyStore.getInstance("jks");
//         keyStore.load(keyStoreFileInputStream, keyStorePassword.toCharArray());
////         KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
////         keyManagerFactory.init(keyStore, clientSslKeyStorePassword.toCharArray());
//
////         KeyStore trustStore = KeyStore.getInstance("jks");
////         trustStore.load(trustStoreFileInputStream, trustStorePassword.toCharArray());
////         TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
////         trustManagerFactory.init(trustStore);
//
////         File keyStoreFile = new File(keyStorePath);
//         File trustStoreFile = new File(trustStorePath);
//
//         return SSLContextBuilder.create()
//                 .loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
//                 .loadTrustMaterial(trustStoreFile, trustStorePassword.toCharArray())
//                 .build();
////                 .forClient()
////                 .keyManager(keyManagerFactory)
////                 .trustManager(trustManagerFactory)
////                 .build();
//
//      } catch (Exception exception) {
//         System.out.println(exception);
//      }
//
//      return null;
//   }

//   public WebClient webClient() {
//      SSLContext sslContext = getTwoWaySslContext();
//      HttpClient httpClient = HttpClient.newBuilder().sslContext(sslContext).build();
//      return WebClient.builder().baseUrl("https://localhost:8082").clientConnector(new ReactorClientHttpConnector(httpClient)).build();
//   }

//   public HttpClient httpClient() {
//      SSLContext sslContext = getTwoWaySslContext();
//      return HttpClient.newBuilder().sslContext(sslContext).build();
//   }


   }