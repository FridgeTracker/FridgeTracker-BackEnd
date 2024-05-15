package com.example.FridgeTracker.MealRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MealRecordRepository extends JpaRepository<MealRecord, UUID> {

    List<MealRecord> findByMemberId(UUID memberId);

}
