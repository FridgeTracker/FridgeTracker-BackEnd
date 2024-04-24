package com.example.FridgeTracker.Notifications;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class NotificationsController {

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private UserRepository userRepository;

    private final NotificationsService notificationsService;

    @Autowired
    public NotificationsController(NotificationsService notificationsService){
        this.notificationsService = notificationsService;
    }

    @PostMapping("/createAlert")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> createAlert(@RequestBody NotificationRequest notification) {

        Optional<User> optionalUser = userRepository.findById(notification.getUserID());

        if(optionalUser.isPresent()){
            notification.getNotification().setUser(optionalUser);
            notificationsRepository.save(notification.getNotification());
            return ResponseEntity.ok("Created Notification");
        } else{
            return ResponseEntity.ok("Failed to create Notification");
        }
    }

    @GetMapping("/getAlerts/{id}")
    @CrossOrigin(origins = "*")
    @Transactional
    public ResponseEntity<String> getAlerts(@PathVariable UUID id){
        return notificationsService.generateAlerts(id);
    }
}
