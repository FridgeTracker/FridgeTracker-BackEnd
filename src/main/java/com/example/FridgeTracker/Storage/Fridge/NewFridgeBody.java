package com.example.FridgeTracker.Storage.Fridge;

import java.util.UUID;

import lombok.Data;

@Data
public class NewFridgeBody {

    private UUID userID;
    private String storageName;
    private long capacity;
    
}
