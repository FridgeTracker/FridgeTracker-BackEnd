package com.example.FridgeTracker.ShoppingList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService){
        this.shoppingListService  = shoppingListService;

    }
    // CALLL METHOD HERE FROM SHOPPINGLIST SERVICE

    
}
