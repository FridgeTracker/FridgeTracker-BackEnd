package com.example.FridgeTracker.Storage.Freezer;
import java.util.UUID;
import lombok.Data;

@Data
public class NewFreezerBody extends Freezer{

    private UUID userID;
    
}
