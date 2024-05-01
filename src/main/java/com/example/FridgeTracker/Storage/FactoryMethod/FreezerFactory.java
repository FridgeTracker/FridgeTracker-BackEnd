package com.example.FridgeTracker.Storage.FactoryMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Freezer.FreezerRepository;

@Component
public class FreezerFactory implements StorageFactory{

    @Autowired
    private FreezerRepository freezerRepository;

    @Override
    public Storage createStorage() {
        return new Freezer();
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