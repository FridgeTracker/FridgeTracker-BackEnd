package com.example.FridgeTracker.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Freezer.FreezerRepository;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.Storage.Fridge.FridgeRepository;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ItemController {

    //T
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FridgeRepository fridgeRepository;

    @Autowired
    private FreezerRepository freezerRepository;


    @PostMapping("/addItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addItemToFridge(@RequestBody ItemBody request){

        Optional<Fridge> fridge = fridgeRepository.findById(request.getId());
        Optional<Freezer> freezer = freezerRepository.findById(request.getId());

        if (fridge.isPresent() || freezer.isPresent()){

            Item item = new Item();

            item.setCalories(request.getCalories());
            item.setFoodName(request.getFoodName());
            item.setQuantity(request.getQuantity());
            item.setType(request.getType());

            if(freezer.isPresent()){item.setFreezer(freezer);}
            else{item.setFridge(fridge);}   

            itemRepository.save(item);

            return ResponseEntity.ok("Item added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add Item");
        }
    }

    @PostMapping("/updateItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateItemInFridge(@RequestBody ItemBody request){
   
        Optional<Fridge> fridgeOptional = fridgeRepository.findById(request.getId());

        if(fridgeOptional.isPresent()){
            Fridge fridge = fridgeOptional.get();

            Optional<Item> itemOptional = fridge.getItems().stream()
                                    .filter(item -> item.getItemID().equals(request.getItemID()))
                                    .findFirst();

            if (itemOptional.isPresent()) {
                Item item = itemOptional.get();
    
                // Update item properties here
                item.setCalories(request.getCalories());
                item.setFoodName(request.getFoodName());
                item.setQuantity(request.getQuantity());
                item.setType(request.getType());
                
                // Save the updated fridge back to the database
                fridgeRepository.save(fridge);

                return ResponseEntity.ok("Item updated successfully");
            } else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in the fridge");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fridge failed to open");
        }
    }

    
    @PostMapping("/deleteItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteItemInFridge(@RequestBody DeleteItemRequest request){
   
        Optional<Fridge> fridgeOptional = fridgeRepository.findById(request.getId());
      
        if (fridgeOptional.isPresent()) {
            Fridge fridge = fridgeOptional.get();
      
            Optional<Item> itemOptional = fridge.getItems().stream()
                                    .filter(item -> item.getItemID().equals(request.getItemID()))
                                    .findFirst();

            if (itemOptional.isPresent()) {
                Item itemToRemove = itemOptional.get();
                fridge.getItems().remove(itemToRemove);
                fridgeRepository.save(fridge); 
                return ResponseEntity.ok("Item deleted successfully.");
            } else {
                return ResponseEntity.badRequest().body("no item id" + request.getId());
            }    
    } else {
        return ResponseEntity.badRequest().body("Fridge not found with ID: " + request.getId());
    }  

    }
}
