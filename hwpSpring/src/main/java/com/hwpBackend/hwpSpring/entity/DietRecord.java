package com.hwpBackend.hwpSpring.entity;

import java.util.Date;

public class DietRecord {
    private int DietRecordID;
    private String MemberID;
    private int DietInfoID;
    private Date Record;
    private String TimeOfMeal;
    private int TotalCalories;

    // constructor
    public DietRecord(int dietRecordID, String memberID, int dietInfoID, Date record, String timeOfMeal, int totalCalories) {
        DietRecordID = dietRecordID;
        MemberID = memberID;
        DietInfoID = dietInfoID;
        Record = record;
        TimeOfMeal = timeOfMeal;
        TotalCalories = totalCalories;
    }

    // getter setter
    public int getDietRecordID() {
        return DietRecordID;
    }

    public void setDietRecordID(int dietRecordID) {
        DietRecordID = dietRecordID;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public int getDietInfoID() {
        return DietInfoID;
    }

    public void setDietInfoID(int dietInfoID) {
        DietInfoID = dietInfoID;
    }

    public Date getRecord() {
        return Record;
    }

    public void setRecord(Date record) {
        Record = record;
    }

    public String getTimeOfMeal() {
        return TimeOfMeal;
    }

    public void setTimeOfMeal(String timeOfMeal) {
        TimeOfMeal = timeOfMeal;
    }

    public int getTotalCalories() {
        return TotalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        TotalCalories = totalCalories;
    }
}
