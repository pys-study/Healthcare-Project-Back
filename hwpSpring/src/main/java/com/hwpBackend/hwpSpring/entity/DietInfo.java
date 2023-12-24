package com.hwpBackend.hwpSpring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DietInfo {

    @Id
    private int DietInfoID;
    private String DietName;
    private int Calories;
    private int Carbohydrate;
    private int Protein;
    private int Fats;

    // constructor
    public DietInfo(int dietInfoID, String dietName, int calories, int carbohydrate, int protein, int fats) {
        DietInfoID = dietInfoID;
        DietName = dietName;
        Calories = calories;
        Carbohydrate = carbohydrate;
        Protein = protein;
        Fats = fats;
    }

    // getter setter
    public int getDietInfoID() {
        return DietInfoID;
    }


    public String getDietName() {
        return DietName;
    }


    public int getCalories() {
        return Calories;
    }


    public int getCarbohydrate() {
        return Carbohydrate;
    }


    public int getProtein() {
        return Protein;
    }


    public int getFats() {
        return Fats;
    }

}
