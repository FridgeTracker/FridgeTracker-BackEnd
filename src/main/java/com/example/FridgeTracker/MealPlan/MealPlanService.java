package com.example.FridgeTracker.MealPlan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class MealPlanService {
    private final MealPlanRepository mealPlanRepository;
    private static final Logger logger = LoggerFactory.getLogger(MealPlanService.class);

    @Autowired
    public MealPlanService(MealPlanRepository mealPlanRepository) {
        this.mealPlanRepository = mealPlanRepository;
    }

    public MealPlan createMealPlan(MealPlan mealPlan) {
        try {
            logger.info("Starting to add meal plan");
            return mealPlanRepository.save(mealPlan);
        } catch (Exception e) {
            logger.error("An error occurred while saving the meal plan: ", e);
            logger.error("Error message: {}", e.getMessage());
            logger.error("Stack trace: {}", e.getStackTrace());
            return null;
        }
    }

    public List<MealPlan> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }

    public Optional<MealPlan> getMealPlanById(UUID id) {
        return mealPlanRepository.findById(id);
    }

    public List<MealPlan> readMealPlansFromJson(String filePath) {
         try {
        Gson gson = new Gson();
        InputStream inputStream = MealPlanService.class.getClassLoader().getResourceAsStream("meal_plans.json");
        if (inputStream == null) {
            logger.error("InputStream is null, file not found in classpath");
        } else {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type mealPlanListType = new TypeToken<List<MealPlan>>() {}.getType();
            List<MealPlan> mealPlans = gson.fromJson(reader, mealPlanListType);
            return mealPlans;
        }
    } catch (Exception e) {
        throw new RuntimeException("Error reading meal plans from JSON file", e);
    }
    return null;
    }

    public void loadMealPlansFromJson(String filePath) {
        List<MealPlan> mealPlans = readMealPlansFromJson(filePath);
        mealPlanRepository.saveAll(mealPlans);
    }
}
