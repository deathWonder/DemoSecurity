package com.example.demosecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {


    @GetMapping("/hello")
    private String hello() {
        return "Hello, Everyone!";
    }

    @GetMapping("/admin")
    private String admin() {
        return "Hello, Admin!";
    }

    @GetMapping("/user")
    private String user() {
        return "Hello, User!";
    }

}
