package com.example.FridgeTracker.Notifications;

import java.util.UUID;

import lombok.Data;

@Data
public class NotificationRequest {

    Notifications notification;
    UUID userID;

}
