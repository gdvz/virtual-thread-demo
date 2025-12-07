package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoThreadController {

    @GetMapping("/testThread")
    public String test() throws InterruptedException {
        Thread.sleep(1000); // blocking OK
        System.out.println("Thread: " + Thread.currentThread());
        return "Thread: " + Thread.currentThread();
    }

}
