package com.hwpBackend.hwpSpring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ExerciseInfo {
    public ExerciseInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ExerciseInfoID = 0;
    private String ExerciseType;
    private String ExerciseName;
    private Integer CaloriesPerMinutes;


    // constructor
    public ExerciseInfo(String exerciseType, String exerciseName, Integer caloriesPerMinutes) {
        ExerciseType = exerciseType;
        ExerciseName = exerciseName;
        CaloriesPerMinutes = caloriesPerMinutes;
    }

    // getter setter

    public Integer getExerciseInfoID() {
        return ExerciseInfoID;
    }


    public String getExerciseType() {
        return ExerciseType;
    }


    public String getExerciseName() {
        return ExerciseName;
    }


    public Integer getCaloriesPerMinutes() {
        return CaloriesPerMinutes;
    }


    public void setExerciseInfoID(Integer exerciseInfoID) {
        ExerciseInfoID = exerciseInfoID;
    }

    public void setExerciseType(String exerciseType) {
        ExerciseType = exerciseType;
    }

    public void setExerciseName(String exerciseName) {
        ExerciseName = exerciseName;
    }

    public void setCaloriesPerMinutes(Integer caloriesPerMinutes) {
        CaloriesPerMinutes = caloriesPerMinutes;
    }

}
