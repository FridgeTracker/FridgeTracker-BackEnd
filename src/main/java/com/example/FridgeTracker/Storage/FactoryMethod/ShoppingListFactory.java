package com.example.FridgeTracker.Storage.FactoryMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingList;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingListRepository;

@Component
public class ShoppingListFactory implements StorageFactory{

     @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Override
    public Storage createStorage() {
        return new ShoppingList();
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
