package com.example.FridgeTracker.Fridge;

import lombok.Data;

@Data
public class NewFridgeBody {
    private String userEmail;
    private String fridgeName;
    private long capacity;
}
