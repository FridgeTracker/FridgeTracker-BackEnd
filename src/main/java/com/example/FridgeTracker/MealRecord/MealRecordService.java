package com.example.FridgeTracker.MealRecord;

import com.example.FridgeTracker.MealRecord.MealRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealRecordService {

    private final MealRecordRepository mealRecordRepository;

    @Autowired
    public MealRecordService(MealRecordRepository mealRecordRepository) {
        this.mealRecordRepository = mealRecordRepository;
    }

    public MealRecord createMealRecord(MealRecord mealRecord) {
        return mealRecordRepository.save(mealRecord);
    }

    public List<MealRecord> getAllMealRecords() {
        return mealRecordRepository.findAll();
    }
}
