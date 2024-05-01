package com.example.FridgeTracker.MealPlan;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="meal_plan")
@AllArgsConstructor
@NoArgsConstructor
public class MealPlan {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="meal_name")
    private String mealName;

    @Column(name="meal_type")
    private String mealType;

    @Column(name="meal_image")
    private String mealImage;

    @Column(name="description")
    private String description;

    @ElementCollection
    @jakarta.persistence.CollectionTable(name = "ingredients", joinColumns = @JoinColumn(name = "meal_plan_id"))
    @Column(name = "ingredients")
    private List<String> ingredients;

    @ElementCollection
    @jakarta.persistence.CollectionTable(name = "nutritional_information", joinColumns = @JoinColumn(name = "meal_plan_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> nutritionalInformation;


}
