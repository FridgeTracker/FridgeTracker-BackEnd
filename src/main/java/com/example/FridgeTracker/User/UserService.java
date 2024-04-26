package com.example.FridgeTracker.User;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.DataSets.FoodData;
import com.example.FridgeTracker.DataSets.FoodDataRepository;
import com.example.FridgeTracker.Item.Item;
import com.example.FridgeTracker.Item.ItemRepository;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Fridge.Fridge;

import java.time.LocalDate;
import java.time.ZoneId;



@Service
public class UserService {

    private final UserRepository userRepository;
    private final FoodDataRepository foodDataRepository;
    private final ItemRepository itemRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, FoodDataRepository foodDataRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.foodDataRepository = foodDataRepository;
    }


    

    //REGISTER USER ENDPOINT **SUBJECT TO CHANGE**
    public ResponseEntity<String> addUser(User user){
        if(user != null){

            if (userRepository.existsByEmail(user.getEmail())) {
                return ResponseEntity.badRequest().body("User already exists");
            }
            //Hash and Set new password
            String hashedPasswordString = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPasswordString);
            user.setExpiryDate(true);
            user.setStorageEmpty(true);
            user.setStorageFull(true);
            //Import into db
            userRepository.save(user);

            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body("Fail to register");
        }

    }

    //LOGIN API ENDPOINT
    public ResponseEntity<?> loginUser(User loginUser){
        User user = userRepository.findByEmail(loginUser.getEmail());

        if (user != null){
            if(passwordEncoder.matches(loginUser.getPassword(), user.getPassword())){
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(999).body("Passwords don't match");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user not found");

    }


    //GET USER OBJECT ENDPOINT
    public ResponseEntity<User> getUser(UUID id){

      // can also use { @RequestHeader("email-tkn") String userEmail  }
        //Add a error check
        Optional<User> user = userRepository.findById(id);
        if(user != null){
            return ResponseEntity.ok(user.get()); 
        } else {
            return ResponseEntity.status(600).body(null); 
        }

    }

    //UPDATE USER ACCOUNT INFO
    public ResponseEntity<String> updateUser(User Request){

        Optional<User> OptUser = userRepository.findById(Request.getId());

        if (OptUser.isPresent()){
            User user = OptUser.get();
            if(Request.getImageData() != null){
                user.setImageData(Request.getImageData());
                userRepository.save(user);
                return ResponseEntity.ok("Profile Picture Saved");
            }
            if (passwordEncoder.matches(Request.getPassword(),user.getPassword())){

                if (Request.getFamilyName()!= ""){
                    user.setFamilyName(Request.getFamilyName());
                }
                if (Request.getEmail()!= ""){
                    user.setEmail(Request.getEmail());
                }
                if(Request.getTimezone() != ""){
                    user.setTimezone(Request.getTimezone());
                }
                userRepository.save(user);
                return ResponseEntity.ok("User information updated.");
            }else{
                return ResponseEntity.ok("Password does not match.");
            }
         
        }

        return ResponseEntity.ok("The new user information is successfully updated.");
    }

    //CHANGE THE PASSWORD
    public ResponseEntity<String> changePassword(PasswordRequest request){

        Optional<User> optUser = userRepository.findById(request.getId());

            if (optUser.isPresent()) {
                User user = optUser.get();
                if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                    String newPassword = request.getNewPw();
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                    return ResponseEntity.ok("Password changed successfully.");
                
                } else {
                    return ResponseEntity.ok("Current password is incorrect.");
                }
            }
        return ResponseEntity.ok("User not found.");
    }


    // GET TIMEZONES
    public List<String> getTimeZones(){
        return ZoneId.getAvailableZoneIds()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }


    public ResponseEntity<String> updateNotifications(User request){

        Optional<User> optUser = userRepository.findById(request.getId());

        if(optUser.isPresent()){
            User user = optUser.get();
            user.setExpiryDate(request.isExpiryDate());
            user.setStorageEmpty(request.isStorageEmpty());
            user.setStorageFull(request.isStorageFull());
            userRepository.save(user);
            return ResponseEntity.ok("Notifications Updated");
        } else{
            return ResponseEntity.ok("Failed to find user");
        }

    }

    public ResponseEntity<String> fillFridgeAndFreezer(UUID userID){

        Optional<User> user = userRepository.findById(userID);
        Random random = new Random();

        if(user.isPresent()){

            for(Fridge fridge:user.get().getFridges()){

                while(fridge.getItems().size() < fridge.getCapacity()){

                    Item item  = new Item();
                    item.setExpiryDate(generateRandomDate());
                    FoodData foodData = foodDataRepository.findById(random.nextInt(2096,2342) + 1);
                    item.setFoodID(foodData);
                    item.setFoodName(foodData.getFoodItem());
                    item.setQuantity(random.nextInt(5) + 1);

                    item.setFridge(Optional.of(fridge));

                    fridge.getItems().add(item);

                    itemRepository.save(item);

                }
            }

            for(Freezer freezer:user.get().getFreezers()){

                while(freezer.getItems().size() < freezer.getCapacity()){

                    Item item  = new Item();
                    item.setExpiryDate(generateRandomDate());
                    FoodData foodData = foodDataRepository.findById(random.nextInt(2096,2342) + 1);
                    item.setFoodID(foodData);
                    item.setFoodName(foodData.getFoodItem());
                    item.setQuantity(random.nextInt(5) + 1);

                    item.setFreezer(Optional.of(freezer));

                    freezer.getItems().add(item);

                    itemRepository.save(item);

                }
            }

            return ResponseEntity.ok("Filled Fridge and Freezer");
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
