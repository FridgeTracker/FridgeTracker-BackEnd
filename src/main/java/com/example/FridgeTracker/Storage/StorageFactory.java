package com.example.FridgeTracker.Storage;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Freezer.FreezerRepository;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.Storage.Fridge.FridgeRepository;
import com.example.FridgeTracker.User.User;

public interface StorageFactory {
    Storage createStorage(Optional<User> user);
    void save(Storage storage);
    void delete(Storage storage);
}


@Component
class FridgeFactory implements StorageFactory{

    @Autowired
    private FridgeRepository fridgeRepository;

    @Override
    public Storage createStorage(Optional<User> user) {
        Fridge fridge = new Fridge();
        fridge.setUser(user);;
        return fridge;
    }

    @Override
    public void save(Storage storage) {
        fridgeRepository.save((Fridge) storage); 
    }

    @Override
    public void delete(Storage storage){
        fridgeRepository.delete((Fridge)storage);
    }
}


@Component
class FreezerFactory implements StorageFactory{

    @Autowired
    private FreezerRepository freezerRepository;

    @Override
    public Storage createStorage(Optional<User> user) {
        Freezer freezer = new Freezer();
        freezer.setUser(user);
        return freezer;
    }

    @Override
    public void save(Storage storage) {
        freezerRepository.save((Freezer) storage); 
    }

    @Override
    public void delete(Storage storage){
        freezerRepository.delete((Freezer) storage);
    }

    
}