package com.hwp.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/")
    public String checkDefaultpage() { return "api is healthy";}
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World v1";
    }

    @GetMapping("/hello-world/{id}")
    public String helloWorldPathVariable(@PathVariable String id) {
        return String.format("Hello World, %s", id);
    }

    @GetMapping("/hello-world-test")
    public String helloWorldCICD() { return "CI/CD TEST COMPLETE"; }
}
