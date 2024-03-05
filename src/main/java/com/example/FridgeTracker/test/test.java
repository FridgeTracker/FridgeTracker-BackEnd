package com.example.FridgeTracker.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class test {

    @GetMapping("/hi")
    public String sayHi() {
        return "Hi there!";
    }

	@GetMapping("/ok")

    String home2() {
      return "Hello World2!";
    }
}