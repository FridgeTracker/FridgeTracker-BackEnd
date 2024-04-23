package com.example.FridgeTracker.ShoppingList;

import java.util.UUID;

import lombok.Data;

@Data
public class ShoppingListRequest {
    ShoppingList shoppingList;
    UUID userID;
}
