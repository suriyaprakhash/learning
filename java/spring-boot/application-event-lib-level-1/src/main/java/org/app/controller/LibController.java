package org.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lib")
public class LibController {

    @GetMapping
    public String testGetMethod() {
        return "hello from lib";
    }
}
