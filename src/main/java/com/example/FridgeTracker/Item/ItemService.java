package com.example.FridgeTracker.Item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.DataSets.FoodData;
import com.example.FridgeTracker.DataSets.FoodDataRepository;
import com.example.FridgeTracker.ShoppingList.ShoppingList;
import com.example.FridgeTracker.ShoppingList.ShoppingListRepository;
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
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository,FridgeRepository fridgeRepository,FreezerRepository freezerRepository,FoodDataRepository foodDataRepository, ShoppingListRepository shoppingListRepository){
    
        this.itemRepository = itemRepository;
        this.fridgeRepository = fridgeRepository;
        this.freezerRepository = freezerRepository;
        this.foodDataRepository = foodDataRepository;
        this.shoppingListRepository = shoppingListRepository;
    }
    //ADD ITEM TO FRIDGE
    public ResponseEntity<String> addItemToFridge( ItemBody request){

        Optional<Fridge> fridgeOptional  = fridgeRepository.findById(request.getId());
        Optional<Freezer> freezerOptional = freezerRepository.findById(request.getId());
        Optional<ShoppingList> shoppingListOptional  = shoppingListRepository.findById(request.getId());

        if (fridgeOptional.isPresent() || freezerOptional.isPresent() || shoppingListOptional.isPresent()){

            Item item = new Item();
            Optional<FoodData> food_item = Optional.empty();
            
            if(request.getFoodID() != null){
                food_item = foodDataRepository.findById(request.getFoodID());
                item.setFoodID(food_item.get());
            }

            item.setFoodName(request.getFoodName());
            item.setQuantity(request.getQuantity());

            if(request.getExpiryDate() != null){
                item.setExpiryDate(request.getExpiryDate());
            }

            item.setFreezer((freezerOptional.isPresent() ? freezerOptional : null));
            item.setFridge((fridgeOptional.isPresent() ? fridgeOptional : null));
            item.setShoppingList((shoppingListOptional.isPresent() ? shoppingListOptional.get() : null));
            

            itemRepository.save(item);

            return ResponseEntity.ok("Item added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add Item");
        }
    }


    //UPDATE ITEM IN FRIDGE
    public ResponseEntity<String> updateItemInFridge(ItemBody request){
   
        Optional<Fridge> fridgeOptional = fridgeRepository.findById(request.getId());
        Optional<Freezer> freezerOptional = freezerRepository.findById(request.getId());
        Optional<ShoppingList> listOptional = shoppingListRepository.findById(request.getId());

        Fridge fridge = null;
        Freezer freezer = null;
        ShoppingList list = null;
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
        else if(listOptional.isPresent()){

            list = listOptional.get();
            itemOptional = list.getItems().stream()
                                    .filter(item -> item.getItemID().equals(request.getItemID()))
                                    .findFirst();
    }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("failed to open");
        }

        if (itemOptional.isPresent()) {
            
            boolean shouldDelete = false;

            while(true){
                Item item = itemOptional.get();

                item.setQuantity(request.getQuantity());

                if(item.getQuantity() == 0){
                    shouldDelete = true;
                }

                if(request.getFoodName() != null){
                    item.setFoodName(request.getFoodName());
                }
                if(request.getExpiryDate() != null){
                    item.setExpiryDate(request.getExpiryDate());
                }
                break;
            }

            if(shouldDelete){
                deleteItemInFridge(request);
                return ResponseEntity.ok("Item deleted successfully");
            }
            
            // Save the updated fridge back to the database
            if(fridgeOptional.isPresent()){
                fridgeRepository.save(fridge);
            }else if(freezerOptional.isPresent()){
                freezerRepository.save(freezer);
            }else if(listOptional.isPresent()){
                shoppingListRepository.save(list);
            }

            return ResponseEntity.ok("Item updated successfully");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in the fridge");
        }
    }


    // DELETE ITEM IN FRIDGE
    public ResponseEntity<String> deleteItemInFridge(ItemBody request){
   
        Optional<Fridge> fridgeOptional = fridgeRepository.findById(request.getId());
        Optional<Freezer> freezerOptional = freezerRepository.findById(request.getId());
        Optional<ShoppingList> listOptional = shoppingListRepository.findById(request.getId());

        Fridge fridge = null;
        Freezer freezer = null;
        ShoppingList list = null;

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
        else if(listOptional.isPresent()){

            list = listOptional.get();
            itemOptional = list.getItems().stream()
                                    .filter(item -> item.getItemID().equals(request.getItemID()))
                                    .findFirst();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("failed to open");
        }

        if (itemOptional.isPresent()) {
            Item itemToRemove = itemOptional.get();

            if(fridgeOptional.isPresent()){
                fridge.getItems().remove(itemToRemove);
                fridgeRepository.save(fridge); 
            }else if(freezerOptional.isPresent()){
                freezer.getItems().remove(itemToRemove);
                freezerRepository.save(freezer); 
            }else if(listOptional.isPresent()){
                list.getItems().remove(itemToRemove);
                shoppingListRepository.save(list); 
            }
        
            return ResponseEntity.ok("Item deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("no item id" + request.getId());
        }    
    

    }
}
