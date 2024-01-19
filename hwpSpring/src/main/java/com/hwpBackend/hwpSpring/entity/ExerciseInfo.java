package com.hwpBackend.hwpSpring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private Integer exerciseInfoID;
    private String exerciseType;
    private String exerciseName;
    private Integer caloriesPerMinutes;


}
