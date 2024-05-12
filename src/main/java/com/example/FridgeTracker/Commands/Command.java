package com.example.FridgeTracker.Commands;

import org.springframework.http.ResponseEntity;

public interface Command {
    ResponseEntity<?> execute();
}
