package com.example.FridgeTracker.Notifications;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void generateFridgeAlerts(Optional<User> optionalUser){
        User user = optionalUser.get();
        List<Fridge> fridges = user.getFridges();
        List<Notifications> notifications = new ArrayList<>();
        
        for(Fridge fridge : fridges){
            if(fridge.getItems().size() == 0){
                Notifications nRequest = new Notifications();
                nRequest.setUser(optionalUser);
                nRequest.setSender("System");
                nRequest.setMessage(fridge.getStorageName() + " is empty.");
                nRequest.setAlert_type("Alert");
                nRequest.setDateTime(LocalDateTime.now());
                notifications.add(nRequest);
            }
        }
        notificationsRepository.saveAll(notifications);
    }

    public void generateFreezerAlerts(Optional<User> optionalUser){
        User user = optionalUser.get();
        List<Freezer> freezers = user.getFreezers();
        List<Notifications> notifications = new ArrayList<>();
        
        for(Freezer freezer : freezers){
            if(freezer.getItems().size() == 0){
                Notifications nRequest = new Notifications();
                nRequest.setUser(optionalUser);
                nRequest.setSender("System");
                nRequest.setMessage(freezer.getStorageName() + " is 0/" +freezer.getCapacity()+". (empty)");
                nRequest.setAlert_type("Alert");
                nRequest.setDateTime(LocalDateTime.now());
                notifications.add(nRequest);
            }
        }
        notificationsRepository.saveAll(notifications);
    }


    
}
