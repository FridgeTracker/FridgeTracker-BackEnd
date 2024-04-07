package com.example.FridgeTracker.Item;
import java.util.UUID;
import lombok.Data;

@Data
public class ItemBody {

    private Long itemID;
    private String foodName;
    private int quantity;
    private Long calories;
    private String type;
    private UUID id;
    
}
