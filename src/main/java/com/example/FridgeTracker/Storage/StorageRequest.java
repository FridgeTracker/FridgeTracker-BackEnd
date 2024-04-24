package com.example.FridgeTracker.Storage;

import java.util.UUID;
import lombok.Data;

@Data
public class StorageRequest extends Storage{
    private UUID userID;
}
