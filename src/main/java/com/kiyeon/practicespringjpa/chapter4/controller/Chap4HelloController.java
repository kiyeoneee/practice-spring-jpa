package com.kiyeon.practicespringjpa.chapter4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chap4")
public class Chap4HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
