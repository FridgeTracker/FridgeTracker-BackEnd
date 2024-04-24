package com.example.FridgeTracker.Storage.Fridge;
import java.util.UUID;
import lombok.Data;

@Data
public class NewFridgeBody extends Fridge{
    private UUID userID;
}
