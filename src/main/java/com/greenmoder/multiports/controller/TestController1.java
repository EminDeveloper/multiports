package com.greenmoder.multiports.controller;

import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/controller1")
public class TestController1 {

    @GetMapping("/hello")
    String hello(HttpServletRequest request) {
        if (request.getLocalPort() == 8888) {
            return "Hello from controller 1: " + request.getLocalPort();
        }
        if (request.getLocalPort() == 8889) {
            return "Hello from controller 2: " + request.getLocalPort();
        }

        return "no repsonse ";
    }
}