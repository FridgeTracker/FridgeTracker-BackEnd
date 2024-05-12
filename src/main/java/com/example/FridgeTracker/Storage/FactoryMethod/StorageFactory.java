package com.example.FridgeTracker.Storage.FactoryMethod;

import java.util.Optional;

import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.User.User;

public interface StorageFactory {
    Storage createStorage(Optional<User> userOptional);
    void save(Storage storage);
    void delete(Storage storage);
}
