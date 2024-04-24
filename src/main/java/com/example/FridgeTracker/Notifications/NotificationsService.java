package com.example.FridgeTracker.Notifications;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.Item.Item;
import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

@Service
public class NotificationsService {

    private final NotificationsRepository notificationsRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationsService(NotificationsRepository notificationsRepository, UserRepository userRepository){
        this.notificationsRepository=notificationsRepository;
        this.userRepository=userRepository;
    }

    public ResponseEntity<String> generateAlerts(UUID id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){

            notificationsRepository.deleteAllByUser_Id(id);

           generateFridgeAlerts(optionalUser);
           generateFreezerAlerts(optionalUser);

            return ResponseEntity.ok("alerts generated");
        } 
        else{
            return ResponseEntity.notFound().build();
        }
    }

    public void generateFridgeAlerts(Optional<User> optionalUser){
        User user = optionalUser.get();
        List<Fridge> fridges = user.getFridges();
        List<Notifications> notifications = new ArrayList<>();
        
        for(Fridge fridge : fridges){
            List<Item> items = fridge.getItems();

            if(fridge.getItems().isEmpty() ||fridge.getItems().size() == fridge.getCapacity()){
                Notifications noti = createFridgeFreezerAlert(fridge);
                noti.setUser(optionalUser);
                notifications.add(noti);
            }

            for(Item item: items){
                if(item.getExpiryDate().isBefore(LocalDate.now())){
                    Notifications noti = createNotification(item.getFoodName() + " expired on " + item.getExpiryDate(), "Notification");
                    noti.setUser(optionalUser);
                    notifications.add(noti);
                }
            }
        }

        notificationsRepository.saveAll(notifications);
    }

    public void generateFreezerAlerts(Optional<User> optionalUser){
        User user = optionalUser.get();
        List<Freezer> freezers = user.getFreezers();
        List<Notifications> notifications = new ArrayList<>();
        
        for(Freezer freezer : freezers){
            List<Item> items = freezer.getItems();

            if(freezer.getItems().isEmpty() ||freezer.getItems().size() == freezer.getCapacity()){
                Notifications noti = createFridgeFreezerAlert(freezer);
                noti.setUser(optionalUser);
                notifications.add(noti);
            }

            for(Item item: items){
                if(item.getExpiryDate().isBefore(LocalDate.now())){
                    Notifications noti = createNotification(item.getFoodName() + " expired on " + item.getExpiryDate(), "Notification");
                    noti.setUser(optionalUser);
                    notifications.add(noti);
                }
            }

        }
        notificationsRepository.saveAll(notifications);
    }

    public Notifications createFridgeFreezerAlert(Storage storage) {
        if (storage instanceof Fridge) {
            Fridge fridge = (Fridge) storage;
            if (fridge.getItems().isEmpty()) {
                return createNotification(fridge.getStorageName() + " is 0/" + fridge.getCapacity() + ". (EMPTY)", "Alert");
            } else if (fridge.getItems().size() == fridge.getCapacity()) {
                return createNotification(fridge.getStorageName() + " is " + fridge.getCapacity() + "/" + fridge.getCapacity() + ". (FULL)", "Alert");
            }
        } else if (storage instanceof Freezer) {
            Freezer freezer = (Freezer) storage;
            if (freezer.getItems().isEmpty()) {
                return createNotification(freezer.getStorageName() + " is 0/" + freezer.getCapacity() + ". (EMPTY)", "Alert");
            } else if (freezer.getItems().size() == freezer.getCapacity()) {
                return createNotification(freezer.getStorageName() + " is " + freezer.getCapacity() + "/" + freezer.getCapacity() + ". (FULL)", "Alert");
            }
        }
        return null; // Or handle other cases as needed
    }
    private Notifications createNotification(String message, String alert) {
        Notifications notification = new Notifications();
        notification.setSender("System");
        notification.setMessage(message);
        notification.setAlert_type(alert);
        notification.setDateTime(LocalDateTime.now());
        return notification;
    }


    
}
