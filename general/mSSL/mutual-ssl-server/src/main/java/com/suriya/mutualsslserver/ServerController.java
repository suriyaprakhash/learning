package com.suriya.mutualsslserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping
    public String serverData() {
        return "Data from server 3";
    }
}
