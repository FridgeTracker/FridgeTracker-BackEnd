package com.example.FridgeTracker.DataSets;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FoodDataController {

    @Autowired
    private FoodDataRepository foodDataRepository;

    public List<FoodData> readfoodDataFromJson() {
        try {
            Gson gson = new Gson();
            InputStream inputStream = FoodDataController.class.getClassLoader().getResourceAsStream("foodItems.json");
            if (inputStream == null) {
                throw new RuntimeException("Failed to open JSON file");
            } 
            else {
                InputStreamReader reader = new InputStreamReader(inputStream);
                Type foodItems = new TypeToken<List<FoodData>>() {}.getType();
                return gson.fromJson(reader, foodItems);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading meal plans from JSON file", e);
        }

   }


    @PostMapping("/load_data")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> loadItemsToDB() {

        List<FoodData> foodData = readfoodDataFromJson();
        foodDataRepository.saveAll(foodData);

        return ResponseEntity.ok("Items uploaded successfully");
    }

    @GetMapping("/getFoodData")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<FoodData>> getFoodData(){

        List<FoodData> foodDataList = new ArrayList<>();
        foodDataRepository.findAll().forEach(foodDataList::add);
        return ResponseEntity.ok(foodDataList);

    }
}
