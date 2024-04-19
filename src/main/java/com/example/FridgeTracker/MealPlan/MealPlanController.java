package com.example.FridgeTracker.MealPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/meal_plans")
@CrossOrigin(origins = "*")
public class MealPlanController {
    private final MealPlanService mealPlanService;

    @Autowired
    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @PostMapping("/add_meal")
    @CrossOrigin(origins = "*")
    public ResponseEntity<MealPlan> createMealPlan(@RequestBody MealPlan mealPlan) {
        MealPlan savedMealPlan = mealPlanService.createMealPlan(mealPlan);
        if (savedMealPlan == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedMealPlan, HttpStatus.CREATED);
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<MealPlan>> getAllMealPlans() {
        List<MealPlan> mealPlans = mealPlanService.getAllMealPlans();
        return new ResponseEntity<>(mealPlans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<MealPlan> getMealPlanById(@PathVariable UUID id) {
        Optional<MealPlan> mealPlan = mealPlanService.getMealPlanById(id);
        return mealPlan.map(plan -> new ResponseEntity<>(plan, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/load_meal_plans")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> loadMealPlansFromJson() {
        try {
            mealPlanService.loadMealPlansFromJson("../../resources/meal_plans.json");
            return new ResponseEntity<>("Meal plans loaded successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to load meal plans: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
