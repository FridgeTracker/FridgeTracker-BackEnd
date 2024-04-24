package com.example.FridgeTracker.Notifications;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FridgeTracker.User.User;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    void deleteAllByUser(User user);
}
