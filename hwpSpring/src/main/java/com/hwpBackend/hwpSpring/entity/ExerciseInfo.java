package com.hwpBackend.hwpSpring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ExerciseInfo {

    @Id
    @GeneratedValue
    private int ExerciseInfoID;
    private String ExerciseType;
    private String ExerciseName;
    private String CaloriesPerMinutes;

    // constructor
    public ExerciseInfo(int exerciseInfoID, String exerciseType, String exerciseName, String caloriesPerMinutes) {
        ExerciseInfoID = exerciseInfoID;
        ExerciseType = exerciseType;
        ExerciseName = exerciseName;
        CaloriesPerMinutes = caloriesPerMinutes;
    }

    // getter setter

    public int getExerciseInfoID() {
        return ExerciseInfoID;
    }


    public String getExerciseType() {
        return ExerciseType;
    }


    public String getExerciseName() {
        return ExerciseName;
    }


    public String getCaloriesPerMinutes() {
        return CaloriesPerMinutes;
    }

}
