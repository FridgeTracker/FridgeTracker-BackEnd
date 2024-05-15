package com.example.FridgeTracker.MealRecord;

import com.example.FridgeTracker.MealRecord.MealRecord;
import com.example.FridgeTracker.MealRecord.MealRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

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

        Map<UUID, Long> mealTypeCounts = mealRecords.stream()
                .collect(Collectors.groupingBy(MealRecord::getMealId, Collectors.counting()));

        List<MealRecord> result = mealRecords.stream()
                .filter(record -> mealTypeCounts.getOrDefault(record.getMealId(), 0L) >= 2)
                .collect(Collectors.toList());

        Map<UUID, Boolean> uniqueMealTypes = new HashMap<>();
        result = result.stream()
                .filter(record -> uniqueMealTypes.putIfAbsent(record.getMealId(), Boolean.TRUE) == null)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}
