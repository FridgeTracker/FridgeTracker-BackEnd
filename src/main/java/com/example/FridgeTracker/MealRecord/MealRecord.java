package com.example.FridgeTracker.MealRecord;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="meal_records")
@AllArgsConstructor
@NoArgsConstructor
public class MealRecord {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "meal_id", nullable = false)
    private Long mealId;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;
}
