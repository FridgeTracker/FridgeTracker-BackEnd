package com.example.FridgeTracker.Item;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.FridgeTracker.Fridge.FridgeRepository;
import com.example.FridgeTracker.User.UserRepository;

public class ItemController {

    //T
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FridgeRepository fridgeRepository;


    //Get all items within a fridge
    public List<Item> getItemsByFridgeId(String id){

        return null;
    }
    
}
