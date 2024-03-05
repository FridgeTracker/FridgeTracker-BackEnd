package com.example.FridgeTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class FridgeTracker {

    public static void main(String[] args) {
        SpringApplication.run(FridgeTracker.class, args);
    }

}

