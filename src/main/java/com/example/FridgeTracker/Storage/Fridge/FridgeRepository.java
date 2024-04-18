package com.example.FridgeTracker.Storage.Fridge;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FridgeTracker.User.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FridgeRepository extends JpaRepository<Fridge, UUID> {

    List<Fridge> findByUser(User user);

    Optional<Fridge> findById(UUID id);
    
} 
