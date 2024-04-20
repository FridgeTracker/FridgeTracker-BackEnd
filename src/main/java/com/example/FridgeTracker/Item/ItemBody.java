package com.example.FridgeTracker.Item;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Data
public class ItemBody {

    private Long itemID;
    private String foodName;
    private int quantity;
    private Long foodID;
    private LocalDate expiryDate;
    private UUID id;
    
}
