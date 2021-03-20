package com.kiyeon.practicespringjpa.chapter3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chap3")
public class Chap3HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
