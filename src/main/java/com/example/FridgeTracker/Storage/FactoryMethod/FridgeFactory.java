package com.example.FridgeTracker.Storage.FactoryMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.Storage.Fridge.FridgeRepository;

@Component
public class FridgeFactory implements StorageFactory{

    @Autowired
    private FridgeRepository fridgeRepository;

    @Override
    public Storage createStorage() {
        return new Fridge();
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