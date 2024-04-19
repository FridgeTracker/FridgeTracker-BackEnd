package com.example.FridgeTracker.Storage.Freezer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FridgeTracker.User.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FreezerRepository extends JpaRepository<Freezer, UUID> {

    List<Freezer> findByUser(User user);

    Optional<Freezer> findById(UUID id);
    
}
    