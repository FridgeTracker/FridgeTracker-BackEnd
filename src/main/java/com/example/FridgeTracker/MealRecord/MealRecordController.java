package com.example.FridgeTracker.MealRecord;

import com.example.FridgeTracker.MealRecord.MealRecord;
import com.example.FridgeTracker.MealRecord.MealRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/mealRecords")
public class MealRecordController {

    @Autowired
    private MealRecordService mealRecordService;

    @Autowired
    private MealRecordRepository mealRecordRepository;

    @PostMapping
    public ResponseEntity<MealRecord> createMealRecord(@RequestBody MealRecord mealRecord) {
        MealRecord savedRecord = mealRecordService.createMealRecord(mealRecord);
        return ResponseEntity.ok(savedRecord);
    }

    @GetMapping
    public ResponseEntity<List<MealRecord>> getAllMealRecords() {
        List<MealRecord> records = mealRecordService.getAllMealRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{memberId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<MealRecord>> getMemberRecords(@PathVariable UUID memberId){
        List<MealRecord> mealRecords = mealRecordRepository.findByMemberId(memberId);
        Set<UUID> uniqueMealIds = new HashSet<>();
        List<MealRecord> uniqueRecords = new ArrayList<>();

        for (MealRecord record : mealRecords) {
            if (!uniqueMealIds.contains(record.getMealId())) {
                uniqueMealIds.add(record.getMealId());
                uniqueRecords.add(record);
            }
        }
        return ResponseEntity.ok(uniqueRecords);
    }
}
