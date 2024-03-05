package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class RankoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RankoApplication.class, args);
    }

	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello Worl!";
    }

	@RequestMapping("/home")
    @ResponseBody
    String home2() {
      return "Hello World2!";
    }
}

