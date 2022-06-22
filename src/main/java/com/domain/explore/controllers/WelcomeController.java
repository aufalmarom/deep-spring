package com.domain.explore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// endpoint url
@RequestMapping("api/welcome")
// class controller
public class WelcomeController {

    // request method
    @GetMapping
    public String welcome() {
        return "Welcome to Springboot REST API";
    }

    // request method
    @PostMapping
    public String other() {
        return "Data Lain";
    }

}
