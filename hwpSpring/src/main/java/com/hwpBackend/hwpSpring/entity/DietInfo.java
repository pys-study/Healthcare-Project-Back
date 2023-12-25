package com.hwpBackend.hwpSpring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class DietInfo {
    public DietInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer DietInfoID;
    private String DietName;
    private Integer Calories;
    private Integer Carbohydrate;
    private Integer Protein;
    private Integer Fats;

    @OneToMany(mappedBy = "dietInfo")
    @JsonIgnore
    private List<DietRecord> dietRecordList;

    // constructor
    public DietInfo(String dietName, Integer calories, Integer carbohydrate, Integer protein, Integer fats) {
        DietName = dietName;
        Calories = calories;
        Carbohydrate = carbohydrate;
        Protein = protein;
        Fats = fats;
    }

    // getter setter
    public Integer getDietInfoID() {
        return DietInfoID;
    }


    public String getDietName() {
        return DietName;
    }


    public Integer getCalories() {
        return Calories;
    }


    public Integer getCarbohydrate() {
        return Carbohydrate;
    }


    public Integer getProtein() {
        return Protein;
    }


    public Integer getFats() {
        return Fats;
    }

    public List<DietRecord> getDietRecordList() {
        return dietRecordList;
    }

    public void setDietInfoID(Integer dietInfoID) {
        DietInfoID = dietInfoID;
    }

    public void setDietName(String dietName) {
        DietName = dietName;
    }

    public void setCalories(Integer calories) {
        Calories = calories;
    }

    public void setCarbohydrate(Integer carbohydrate) {
        Carbohydrate = carbohydrate;
    }

    public void setProtein(Integer protein) {
        Protein = protein;
    }

    public void setFats(Integer fats) {
        Fats = fats;
    }

    public void setDietRecordList(List<DietRecord> dietRecordList) {
        this.dietRecordList = dietRecordList;
    }
}
