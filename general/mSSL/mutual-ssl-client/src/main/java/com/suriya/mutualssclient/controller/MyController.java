package com.suriya.mutualssclient.controller;

import com.suriya.mutualssclient.SystemPropertySetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/client")
public class MyController {

    @Autowired
    WebClient webClient;
//
//    @Autowired
//    public MyController(WebClient webClient) {
//        this.webClient = webClient;
//    }

    @Autowired
    HttpClient httpClient;

    // https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#howto.webserver.configure-ssl
    @GetMapping
    public String gatherDataFromServer() throws URISyntaxException, IOException, InterruptedException {
        WebClient webClient = WebClient.create("https://localhost:8082");
        Mono<String> dateFromServer = webClient.get()
                .uri("/server")
                .retrieve().bodyToMono(String.class);
        var data = dateFromServer.block();
        return data;
    }

    @GetMapping("webclient-netty")
    public String gatherDataUsingWebClientNetty() throws URISyntaxException, IOException, InterruptedException {
//        webClient.baseUrl("https://localhost:8082");
        Mono<String> dateFromServer = webClient.get()
                .uri("https://localhost:8082/server")
                .retrieve().bodyToMono(String.class);
        var data = dateFromServer.block();
        return data;
    }

    @GetMapping("http")
    public String getDataUsingHttpClient() throws URISyntaxException, IOException, InterruptedException {
//        SystemPropertySetter systemPropertySetter = new SystemPropertySetter();
//        HttpClient httpClient = systemPropertySetter.httpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://localhost:8082/server"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,  HttpResponse.BodyHandlers.ofString());

        // print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

        return response.body().toString();
    }

    @GetMapping("/cli")
    public String gatherDataFromClient() {
        return "data from client";
    }

}
