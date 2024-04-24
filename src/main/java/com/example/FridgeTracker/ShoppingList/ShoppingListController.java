package com.example.FridgeTracker.ShoppingList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/create")
    @CrossOrigin(origins="*")
    public ResponseEntity<String> createNews_list(@RequestBody ShoppingListRequest request){
        return shoppingListService.createNews_list(request);
    }

    @PostMapping("/saveList")
    @CrossOrigin(origins="*")
    public ResponseEntity<String> saveList(@RequestBody ShoppingListRequest request){
        return shoppingListService.saveList(request);
    }

    @PostMapping("/deleteList")
    @CrossOrigin(origins="*")
    public ResponseEntity<String> deleteList(@RequestBody ShoppingList request){
        return shoppingListService.deleteList(request);
    }

    
}
