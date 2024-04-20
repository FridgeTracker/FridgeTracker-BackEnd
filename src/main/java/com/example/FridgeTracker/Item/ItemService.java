package com.example.FridgeTracker.Item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.DataSets.FoodData;
import com.example.FridgeTracker.DataSets.FoodDataRepository;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Freezer.FreezerRepository;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.Storage.Fridge.FridgeRepository;
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final FridgeRepository fridgeRepository;
    private final FreezerRepository freezerRepository;
    private final FoodDataRepository foodDataRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository,FridgeRepository fridgeRepository,FreezerRepository freezerRepository,FoodDataRepository foodDataRepository){
    
        this.itemRepository = itemRepository;
        this.fridgeRepository = fridgeRepository;
        this.freezerRepository = freezerRepository;
        this.foodDataRepository = foodDataRepository;
    }

    public ResponseEntity<String> addItemToFridge( ItemBody request){

        Optional<Fridge> fridge = fridgeRepository.findById(request.getId());
        Optional<Freezer> freezer = freezerRepository.findById(request.getId());

        if (fridge.isPresent() || freezer.isPresent()){

            Item item = new Item();
            
            Optional<FoodData> food_item = foodDataRepository.findById(request.getFoodID());

            if(!food_item.isPresent()){
                System.out.println("cant find food");
            } else{
                System.out.println("find food");
            }

            item.setFoodID(food_item.get());
            item.setFoodName(request.getFoodName());
            item.setQuantity(request.getQuantity());
            item.setExpiryDate(request.getExpiryDate());

            if(freezer.isPresent()){
                item.setFreezer(freezer);
            }
            else{
                item.setFridge(fridge);
            }   

            itemRepository.save(item);

            return ResponseEntity.ok("Item added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add Item");
        }
    }


    
    public ResponseEntity<String> updateItemInFridge( ItemBody request){
   
        Optional<Fridge> fridgeOptional = fridgeRepository.findById(request.getId());
        Optional<Freezer> freezerOptional = freezerRepository.findById(request.getId());

        Fridge fridge = null;
        Freezer freezer = null;
        Optional<Item> itemOptional;

        if (fridgeOptional.isPresent()){

                fridge = fridgeOptional.get();
                itemOptional = fridge.getItems().stream()
                                        .filter(item -> item.getItemID().equals(request.getItemID()))
                                        .findFirst();
        } 
        else if(freezerOptional.isPresent()){

                freezer = freezerOptional.get();
                itemOptional = freezer.getItems().stream()
                                        .filter(item -> item.getItemID().equals(request.getItemID()))
                                        .findFirst();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fridge failed to open");
        }

        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();

            item.setQuantity(request.getQuantity());
            item.setExpiryDate(request.getExpiryDate());
            
            // Save the updated fridge back to the database
            if(fridgeOptional.isPresent()){
                fridgeRepository.save(fridge);
            }else{
                freezerRepository.save(freezer);
            }

            return ResponseEntity.ok("Item updated successfully");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in the fridge");
        }
    }
}
