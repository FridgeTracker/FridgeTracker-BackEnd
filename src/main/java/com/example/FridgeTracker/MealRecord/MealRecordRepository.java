package com.example.FridgeTracker.MealRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface MealRecordRepository extends JpaRepository<MealRecord, UUID> {
}
