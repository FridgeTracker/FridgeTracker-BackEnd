package com.example.FridgeTracker.Commands.StorageCommands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.Storage.StorageType;
import com.example.FridgeTracker.Storage.StorageRequest;
import com.example.FridgeTracker.Storage.FactoryMethod.FreezerFactory;
import com.example.FridgeTracker.Storage.FactoryMethod.FridgeFactory;
import com.example.FridgeTracker.Storage.FactoryMethod.ShoppingListFactory;
import com.example.FridgeTracker.Storage.FactoryMethod.StorageFactory;

public class DeleteStorageCommand implements Command {

    private StorageRequest request;
    private FridgeFactory fridgeFactory;
    private FreezerFactory freezerFactory;
    private ShoppingListFactory shoppingListFactory;

    @Autowired
    public DeleteStorageCommand(StorageRequest request, FridgeFactory fridgeFactory, FreezerFactory freezerFactory, ShoppingListFactory shoppingListFactory){
        this.request = request;
        this.fridgeFactory = fridgeFactory;
        this.freezerFactory = freezerFactory;
        this.shoppingListFactory = shoppingListFactory;
    }

    @Override
    public ResponseEntity<?> execute() {
         try {
            StorageFactory storageFactory;
            if(request.getType() == StorageType.FREEZER){
                storageFactory = freezerFactory;
            } else if(request.getType() == StorageType.FRIDGE){
                storageFactory = fridgeFactory;
            } else{
                storageFactory = shoppingListFactory;
            }

            Storage storage = storageFactory.createStorage(Optional.empty());
            storage.setId(request.getId());
            storageFactory.delete(storage);
            
            return ResponseEntity.ok("Storage deleted successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete storage");
        }
       
    }
    
}
