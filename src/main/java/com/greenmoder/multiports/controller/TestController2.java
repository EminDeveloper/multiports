package com.greenmoder.multiports.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/controller2")
public class TestController2 {

    @GetMapping("/hello")
    String hello(HttpServletRequest request) {
        return "hello from controller 2 " + request.getLocalPort();
    }
}