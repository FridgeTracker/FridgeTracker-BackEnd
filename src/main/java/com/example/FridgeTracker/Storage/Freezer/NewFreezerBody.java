package com.example.FridgeTracker.Storage.Freezer;

import java.util.UUID;

import lombok.Data;

@Data
public class NewFreezerBody {

    private UUID userID;
    private String storageName;
    private long capacity;
    
}
