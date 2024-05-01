package com.example.FridgeTracker.Commands;

import org.springframework.http.ResponseEntity;

public class CommandInvoker {
    private Command command;

    public CommandInvoker(Command command) {
        this.command = command;
    }

    public ResponseEntity<?> executeCommand() {
        return command.execute();
    }

}
