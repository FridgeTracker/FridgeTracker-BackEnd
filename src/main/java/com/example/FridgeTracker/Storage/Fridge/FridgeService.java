package com.example.FridgeTracker.Storage.Fridge;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.DataSets.FoodData;
import com.example.FridgeTracker.DataSets.FoodDataRepository;
import com.example.FridgeTracker.Item.Item;
import com.example.FridgeTracker.Item.ItemRepository;
import com.example.FridgeTracker.Storage.StorageRequest;
import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Service
public class FridgeService {
    private final FridgeRepository fridgeRepository;
    private final UserRepository userRepository;
    private final FoodDataRepository foodDataRepository;
    private final ItemRepository itemRepository;


    @Autowired
    public FridgeService(FridgeRepository fridgeRepository,UserRepository userRepository, FoodDataRepository foodDataRepository, ItemRepository itemRepository){
        this.fridgeRepository = fridgeRepository;
        this.userRepository = userRepository;
        this.foodDataRepository = foodDataRepository;
        this.itemRepository = itemRepository;
    }
   


    public ResponseEntity<String> addFridgeToUser(StorageRequest request){

        Optional<User> userOptional = userRepository.findById(request.getUserID());

        if (userOptional.isPresent()) { // Check if user is present

            Fridge fridge = new Fridge();

            fridge.setStorageName(request.getStorageName());
            fridge.setCapacity(request.getCapacity());
            fridge.setType("Fridge");

            fridge.setUser(userOptional);

            fridgeRepository.save(fridge);

            return ResponseEntity.ok("Fridge added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add fridge");
        }

}

    @Transactional
    public String deleteFridge(UUID fridgeId) {
        Optional<Fridge> optionalFridge = fridgeRepository.findById(fridgeId);
        if (optionalFridge.isPresent()) {
            Fridge fridge = optionalFridge.get();
            fridgeRepository.delete(fridge);
            return "Successfully deleted Fridge";
        } else {
            return "Failed to delete Fridge";
        }
    }


    public ResponseEntity<String> fillFridge(UUID userID){

        Optional<User> user = userRepository.findById(userID);
        Random random = new Random();

        if(user.isPresent()){
            for(Fridge fridge:user.get().getFridges()){

                while(fridge.getItems().size() < fridge.getCapacity()){

                    Item item  = new Item();
                    item.setExpiryDate(generateRandomDate());
                    FoodData foodData = foodDataRepository.findById(random.nextInt(2744) + 1);
                    item.setFoodID(foodData);
                    item.setFoodName(foodData.getFoodItem());
                    item.setQuantity(random.nextInt(5) + 1);
                    item.setFridge(fridgeRepository.findById(userID));

                    fridge.getItems().add(item);

                    itemRepository.save(item);

                }
            }
            return ResponseEntity.ok("Updated Fridge");
        }
        return ResponseEntity.ok("Failed to find user");
    }

    public LocalDate generateRandomDate() {
        LocalDate currentDate = LocalDate.now();
        Random random = new Random();
        int randomOffset = random.nextInt(7);
        int daysOffset = randomOffset - 1;
        LocalDate randomDate = currentDate.plusDays(daysOffset);
        return randomDate;
    }
}
