package com.hwpBackend.hwpSpring.entity;

public class DietInfo {
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

    public void setDietInfoID(int dietInfoID) {
        DietInfoID = dietInfoID;
    }

    public String getDietName() {
        return DietName;
    }

    public void setDietName(String dietName) {
        DietName = dietName;
    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        Calories = calories;
    }

    public int getCarbohydrate() {
        return Carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        Carbohydrate = carbohydrate;
    }

    public int getProtein() {
        return Protein;
    }

    public void setProtein(int protein) {
        Protein = protein;
    }

    public int getFats() {
        return Fats;
    }

    public void setFats(int fats) {
        Fats = fats;
    }
}
