package com.suriya.mutualssclient.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.http.HttpClient;
import java.security.KeyStore;

import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.tcp.SslProvider;

@Slf4j
@Component
public class SslConfig {

   @Value("${suriya.client.ssl.trust-store}")
   private String trustStorePath;

   @Value("${suriya.client.ssl.trust-store-password}")
   private String trustStorePassword;

   @Value("${suriya.client.ssl.key-store}")
   private String keyStorePath;

   @Value("${suriya.client.ssl.key-store-password}")
   private String keyStorePassword;

   @PostConstruct
   public void setProperty() {
//      System.setProperty("javax.net.ssl.trustStore", "C:\\Suriya\\ws\\learning\\general\\mSSL\\mutual-ssl-client\\src\\main\\resources\\certs\\client-truststore.jks");
//      System.setProperty("javax.net.ssl.trustStorePassword", "password");
      System.setProperty("javax.net.ssl.trustStore", trustStorePath);
      System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
      System.setProperty("javax.net.debug", "ssl:handshake");
      System.setProperty("javax.net.debug", "all");
   }


   public SslContext buildSslContextForReactorClientHttpConnector() {

      SslContext sslContext = null;
      try(FileInputStream keyStoreFileInputStream = new FileInputStream(ResourceUtils.getFile(keyStorePath));
          FileInputStream trustStoreFileInputStream = new FileInputStream(ResourceUtils.getFile(trustStorePath));
      ) {
         KeyStore keyStore = KeyStore.getInstance("jks");
         keyStore.load(keyStoreFileInputStream, keyStorePassword.toCharArray());
         KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
         keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

         KeyStore trustStore = KeyStore.getInstance("jks");
         trustStore.load(trustStoreFileInputStream, trustStorePassword.toCharArray());
         TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
         trustManagerFactory.init(trustStore);

         sslContext = SslContextBuilder.forClient()
                 .keyManager(keyManagerFactory)
                 .trustManager(trustManagerFactory)
                 .build();
      } catch (Exception exception) {
        log.error("Exception while building SSL context for reactor web client: ", exception);
      }

     return sslContext;
   }

   public SSLContext buildSslContextForHttpClient() {

      SSLContext sslContext = null;

      try(FileInputStream keyStoreFileInputStream = new FileInputStream(ResourceUtils.getFile(keyStorePath))) {
         KeyStore keyStore = KeyStore.getInstance("jks");
         keyStore.load(keyStoreFileInputStream, keyStorePassword.toCharArray());

         File trustStoreFile = new File(trustStorePath);

         sslContext =  SSLContextBuilder.create()
                 .loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
                 .loadTrustMaterial(trustStoreFile, trustStorePassword.toCharArray())
                 .build();

      } catch (Exception exception) {
         log.error("Exception while building SSL context for reactor http client: ", exception);
      }

      return sslContext;
   }

   @Bean
   public WebClient webClient() {
      SslProvider sslProvider = SslProvider.builder()
              .sslContext(buildSslContextForReactorClientHttpConnector()).build();
      reactor.netty.http.client.HttpClient httpClient = reactor.netty.http.client.HttpClient.create()
              .secure(sslProvider);
      return WebClient.builder()
//              .baseUrl("https://localhost:8082")
              .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
   }

   @Bean
   public HttpClient httpClient() {
      SSLContext sslContext = buildSslContextForHttpClient();
      return HttpClient.newBuilder().sslContext(sslContext).build();
   }

}