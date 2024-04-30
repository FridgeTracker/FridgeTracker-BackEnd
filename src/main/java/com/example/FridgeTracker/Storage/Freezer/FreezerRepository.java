package com.example.FridgeTracker.Storage.Freezer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FridgeTracker.User.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FreezerRepository extends JpaRepository<Freezer, UUID> {

    List<Freezer> findByUser(User user);
    Optional<Freezer> findById(UUID id);
    
}
    