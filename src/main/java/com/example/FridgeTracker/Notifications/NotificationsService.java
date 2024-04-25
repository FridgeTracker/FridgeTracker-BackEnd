package com.example.FridgeTracker.Notifications;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.Item.Item;
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

            if(fridge.getItems().isEmpty() && user.isStorageEmpty()){
                Notifications noti = createNotification(fridge.getStorageName() + " is 0/" + fridge.getCapacity() + ". (EMPTY)", "Alert");
                noti.setUser(optionalUser);
                ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
                ZonedDateTime dateTimeInTimeZone = utcDateTime.withZoneSameInstant(ZoneId.of(user.getTimezone()));
                noti.setDateTime(dateTimeInTimeZone.toLocalDateTime());
                notifications.add(noti);
            }
            if(fridge.getItems().size() == fridge.getCapacity() && user.isStorageFull()){
                Notifications noti = createNotification(fridge.getStorageName() + " is " + fridge.getCapacity() + "/" + fridge.getCapacity() + ". (FULL)", "Alert");
                noti.setUser(optionalUser);
                ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
                ZonedDateTime dateTimeInTimeZone = utcDateTime.withZoneSameInstant(ZoneId.of(user.getTimezone()));
                noti.setDateTime(dateTimeInTimeZone.toLocalDateTime());
                notifications.add(noti);
            }

            if(user.isExpiryDate()){
                for(Item item: items){
                    if(item.getExpiryDate().isBefore(LocalDate.now())){
                    Notifications noti = createNotification(item.getFoodName() +" in " + fridge.getStorageName() + " expired on " + item.getExpiryDate(), "Notification");
                    noti.setUser(optionalUser);
                    notifications.add(noti);
                    }
                    if(item.getExpiryDate().isEqual(LocalDate.now())){
                        Notifications noti = createNotification(item.getFoodName() + " in " + fridge.getStorageName() + " expires TODAY! ", "Reminder");
                        noti.setUser(optionalUser);
                        notifications.add(noti);
                    }
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

            if(freezer.getItems().isEmpty() && user.isStorageEmpty()){
                Notifications noti = createNotification(freezer.getStorageName() + " is 0/" + freezer.getCapacity() + ". (EMPTY)", "Alert");
                noti.setUser(optionalUser);
                notifications.add(noti);
            }
            if(freezer.getItems().size() == freezer.getCapacity() && user.isStorageFull()){
                Notifications noti = createNotification(freezer.getStorageName() + " is " + freezer.getCapacity() + "/" + freezer.getCapacity() + ". (FULL)", "Alert");
                noti.setUser(optionalUser);
                notifications.add(noti);
            }

            if(user.isExpiryDate()){
                for(Item item: items){
                    if(item.getExpiryDate().isBefore(LocalDate.now())){
                        Notifications noti = createNotification(item.getFoodName() + " in " + freezer.getStorageName() + " expired on " + item.getExpiryDate(), "Notification");
                        noti.setUser(optionalUser);
                        notifications.add(noti);
                    }
                    if(item.getExpiryDate().isEqual(LocalDate.now())){
                        Notifications noti = createNotification(item.getFoodName() + " in " + freezer.getStorageName() + " expires TODAY! ", "Reminder");
                        noti.setUser(optionalUser);
                        notifications.add(noti);
                    }
                }
            }

        }
        notificationsRepository.saveAll(notifications);
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
