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
    private Integer ExerciseInfoID;
    private String ExerciseType;
    private String ExerciseName;
    private Integer CaloriesPerMinutes;

    @OneToMany(mappedBy = "exerciseInfo")
    @JsonIgnore
    private List<ExerciseRecord> exerciseRecordList;

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

    public List<ExerciseRecord> getExerciseRecordList() {
        return exerciseRecordList;
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

    public void setExerciseRecordList(List<ExerciseRecord> exerciseRecordList) {
        this.exerciseRecordList = exerciseRecordList;
    }
}
