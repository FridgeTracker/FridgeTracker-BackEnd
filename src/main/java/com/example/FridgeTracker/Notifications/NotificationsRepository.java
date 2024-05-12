package com.example.FridgeTracker.Notifications;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Notifications n WHERE n.user.id = :userId AND n.sender != 'Admin'")
    void deleteAllByUser_Id(UUID userId);

}
