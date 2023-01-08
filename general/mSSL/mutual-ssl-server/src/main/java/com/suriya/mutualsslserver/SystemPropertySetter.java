package com.suriya.mutualsslserver;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemPropertySetter {

//   @Value("${suriya.server.ssl.trust-store}")
//   private String trustStorePath;
//
//   @Value("${suriya.server.ssl.trust-store-password}")
//   private String trustStorePassword;

   @PostConstruct
   public void setProperty() {
//      System.setProperty("javax.net.ssl.trustStore", trustStorePath);
//      System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
      System.setProperty("javax.net.debug", "ssl:handshake");
      System.setProperty("javax.net.debug", "all");
   }

}