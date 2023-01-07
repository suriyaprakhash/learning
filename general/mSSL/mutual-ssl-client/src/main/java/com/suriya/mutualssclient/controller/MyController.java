package com.suriya.mutualssclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.net.http.HttpClient;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/client")
public class MyController {

//    WebClient webClient;
//
//    @Autowired
//    public MyController(WebClient webClient) {
//        this.webClient = webClient;
//    }

    // https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#howto.webserver.configure-ssl
    @GetMapping
    public String gatherDataFromServer() {
        WebClient webClient = WebClient.create("https://localhost:8082");
        Mono<String> dateFromServer = webClient.get()
                .uri("/server")
                .retrieve().bodyToMono(String.class);
        var data = dateFromServer.block();
        return data;
    }




    @GetMapping("/cli")
    public String gatherDataFromClient() {
        return "data from client";
    }

}
