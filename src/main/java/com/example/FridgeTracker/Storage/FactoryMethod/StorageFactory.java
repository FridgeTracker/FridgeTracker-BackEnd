package com.example.FridgeTracker.Storage.FactoryMethod;

import com.example.FridgeTracker.Storage.Storage;

public interface StorageFactory {
    Storage createStorage();
    void save(Storage storage);
    void delete(Storage storage);
}
