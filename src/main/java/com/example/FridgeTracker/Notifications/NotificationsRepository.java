package com.example.FridgeTracker.Notifications;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.FridgeTracker.User.User;

import jakarta.transaction.Transactional;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Notifications n WHERE n.user.id = :userId")
    void deleteAllByUser_Id(UUID userId);

}
