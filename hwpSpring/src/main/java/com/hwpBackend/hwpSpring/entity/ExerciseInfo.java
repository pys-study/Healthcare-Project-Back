package com.hwpBackend.hwpSpring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "exercise_info")
public class ExerciseInfo {

    @Id
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

    public void setExerciseInfoID(int exerciseInfoID) {
        ExerciseInfoID = exerciseInfoID;
    }

    public String getExerciseType() {
        return ExerciseType;
    }

    public void setExerciseType(String exerciseType) {
        ExerciseType = exerciseType;
    }

    public String getExerciseName() {
        return ExerciseName;
    }

    public void setExerciseName(String exerciseName) {
        ExerciseName = exerciseName;
    }

    public String getCaloriesPerMinutes() {
        return CaloriesPerMinutes;
    }

    public void setCaloriesPerMinutes(String caloriesPerMinutes) {
        CaloriesPerMinutes = caloriesPerMinutes;
    }
}
