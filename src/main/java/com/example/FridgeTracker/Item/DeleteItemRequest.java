package com.example.FridgeTracker.Item;
import java.util.UUID;
import lombok.Data;

@Data
public class DeleteItemRequest {
    private Long itemID;
    private UUID id;
}
