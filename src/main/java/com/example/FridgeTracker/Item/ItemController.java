package com.example.FridgeTracker.Item;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.Fridge.Fridge;
import com.example.FridgeTracker.Fridge.FridgeRepository;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ItemController {

    //T
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FridgeRepository fridgeRepository;


    //Get all items within a fridge
    public List<Item> getItemsByFridgeId(String id){

        return null;
    }

    @PostMapping("/addItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addItemToFridge(@RequestBody ItemBody request){

        Optional<Fridge> fridge = fridgeRepository.findById(request.getId());

        if (fridge != null){

            Item item = new Item();

            item.setCalories(request.getCalories());
            item.setFoodName(request.getFoodName());
            item.setQuantity(request.getQuantity());
            item.setType(request.getType());

            item.setFridge(fridge);

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

        if(fridgeOptional != null){
            Fridge fridge = fridgeOptional.get();

            Optional<Item> itemOptional = fridge.getItems().stream()
                                    .filter(item -> item.getFridgeID().equals(request.getItemID()))
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
    public ResponseEntity<String> deleteItemInFridge(@RequestBody ItemBody request){
   
        Optional<Fridge> fridgeOptional = fridgeRepository.findById(request.getId());
        return ResponseEntity.ok("Item deleted successfully.");/* 
        if (fridgeOptional.isPresent()) {
            Fridge fridge = fridgeOptional.get();

            fridge.removeItem(request.getItemID());

            fridgeRepository.save(fridge);

            return ResponseEntity.ok("Item deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Fridge not found with ID: " + request.getId());
        }    */
    }
}
