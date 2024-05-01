package com.example.FridgeTracker.Storage.FactoryMethod;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingList;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingListRepository;
import com.example.FridgeTracker.User.User;

@Component
public class ShoppingListFactory implements StorageFactory{

     @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Override
    public Storage createStorage(Optional<User> userOptional) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(userOptional);
        return shoppingList;
    }

    @Override
    public void save(Storage storage) {
        shoppingListRepository.save((ShoppingList) storage);
    }

    @Override
    public void delete(Storage storage) {
        shoppingListRepository.delete((ShoppingList) storage);
    }
    
}
