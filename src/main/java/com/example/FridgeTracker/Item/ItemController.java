package com.example.FridgeTracker.Item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.DataSets.FoodData;
import com.example.FridgeTracker.DataSets.FoodDataRepository;
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

    @Autowired
    private FoodDataRepository foodDataRepository;

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }


    @PostMapping("/addItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addItemToFridge(@RequestBody ItemBody request){
        return itemService.addItemToFridge(request);

    }

    @PostMapping("/updateItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateItemInFridge(@RequestBody ItemBody request){
        return itemService.updateItemInFridge(request);
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
