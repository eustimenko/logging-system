package com.spring.web.demo.web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping
    public String index() {
        return "Hello, World!";
    }
}
