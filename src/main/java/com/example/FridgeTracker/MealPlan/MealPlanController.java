package com.example.FridgeTracker.MealPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.io.IOException;

@RestController
@RequestMapping("/mealPlans")
@CrossOrigin(origins = "*")
public class MealPlanController {
    private final MealPlanService mealPlanService;

    @Autowired
    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @PostMapping("/loadMealPlans")
    public ResponseEntity<String> loadMealPlans() {
        try {
            mealPlanService.loadMealPlans();
            return ResponseEntity.ok("Meal plans loaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to load meal plans: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MealPlan>> getAllMealPlans() {
        List<MealPlan> mealPlans = mealPlanService.getAllMealPlans();
        if (mealPlans.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(mealPlans);
        }
    }
}
