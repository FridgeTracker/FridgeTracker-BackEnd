package com.example.FridgeTracker.MealPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.io.IOException;
import org.springframework.util.StreamUtils;
import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class MealPlanService {

    private static final String JSON_FILE_PATH = "meal_plans.json";
    private static final String IMAGE_FOLDER_PATH = "downloaded_images/";

    @Autowired
    private MealPlanRepository mealPlanRepository;

    public void loadMealPlans() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource jsonResource = new ClassPathResource(JSON_FILE_PATH);
        
        // Read the JSON file
        String textJson = new String(StreamUtils.copyToByteArray(jsonResource.getInputStream()), StandardCharsets.UTF_8);
        List<MealPlan> mealPlans = mapper.readValue(textJson, new TypeReference<List<MealPlan>>() {});

        for (MealPlan plan : mealPlans) {
            // Construct the path to the image file based on the meal name
            String imageName = plan.getMealName().replaceAll("[^a-zA-Z ]", "") + ".jpg"; 
            imageName = imageName.replaceAll(" ", ""); 
            ClassPathResource imgResource = new ClassPathResource(IMAGE_FOLDER_PATH + imageName);

            // Read the image as a byte array
            byte[] imageBytes;
            try (InputStream is = imgResource.getInputStream()) {
                imageBytes = StreamUtils.copyToByteArray(is);
            } catch (IOException e) {
                imageBytes = new byte[0]; 
                System.err.println("Could not load image for " + plan.getMealName() + ": " + e.getMessage());
            }

            plan.setMealImage(imageBytes);
            mealPlanRepository.save(plan);
        }
    }

    public List<MealPlan> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }

    public Optional<MealPlan> getMealPlanById(UUID id) {
        return mealPlanRepository.findById(id);
    }

    
}
