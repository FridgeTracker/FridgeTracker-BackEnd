package com.example.FridgeTracker.Storage.Fridge;

import lombok.Data;

@Data
public class NewFridgeBody {

    private String userEmail;
    private String storageName;
    private long capacity;
    
}
