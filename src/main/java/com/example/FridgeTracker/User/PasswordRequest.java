package com.example.FridgeTracker.User;

import java.util.UUID;

import lombok.Data;
@Data
public class PasswordRequest {
    UUID id;
    String password;
    String newPw;

}
