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
    
    
}
