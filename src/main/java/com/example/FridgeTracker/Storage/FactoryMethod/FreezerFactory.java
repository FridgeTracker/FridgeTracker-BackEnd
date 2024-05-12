package com.example.FridgeTracker.Storage.FactoryMethod;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Freezer.FreezerRepository;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.User.User;

@Component
public class FreezerFactory implements StorageFactory{

    @Autowired
    private FreezerRepository freezerRepository;

    @Override
    public Storage createStorage(Optional<User> userOptional) {
        Freezer freezer = new Freezer();
        freezer.setUser(userOptional);
        return freezer;
    }

    @Override
    public void save(Storage storage) {
        freezerRepository.save((Freezer) storage); 
    }

    @Override
    public void delete(Storage storage){
        ((Freezer)storage).setItems(Collections.emptyList());
        freezerRepository.delete((Freezer) storage);
    }

    
}