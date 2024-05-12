package com.example.FridgeTracker.Storage.FactoryMethod;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.Storage.Fridge.FridgeRepository;
import com.example.FridgeTracker.User.User;

@Component
public class FridgeFactory implements StorageFactory{

    @Autowired
    private FridgeRepository fridgeRepository;

    @Override
    public Storage createStorage(Optional<User> userOptional) {
        Fridge fridge = new Fridge();
        fridge.setUser(userOptional);
        return fridge;
    }

    @Override
    public void save(Storage storage) {
        fridgeRepository.save((Fridge) storage); 
    }

    @Override
    public void delete(Storage storage){
        ((Fridge)storage).setItems(Collections.emptyList());
        fridgeRepository.delete((Fridge)storage);
    }
}