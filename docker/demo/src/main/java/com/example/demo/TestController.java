package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("hello")
@RestController
public class TestController {

    @GetMapping
    public String hello() {
        log.info("info");
        log.error("error");
        log.warn("warn");
        log.debug("debug");
        return "hello";
    }
}
