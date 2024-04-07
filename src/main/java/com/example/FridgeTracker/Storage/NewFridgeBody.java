package com.example.FridgeTracker.Storage;

import lombok.Data;

@Data
public class NewFridgeBody {

    private String userEmail;
    private String fridgeName;
    private long capacity;
    
}
