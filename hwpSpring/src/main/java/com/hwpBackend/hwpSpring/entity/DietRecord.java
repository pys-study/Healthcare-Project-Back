package com.hwpBackend.hwpSpring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class DietRecord {

    @Id
    @GeneratedValue
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


    public String getMemberID() {
        return MemberID;
    }


    public int getDietInfoID() {
        return DietInfoID;
    }


    public Date getRecord() {
        return Record;
    }


    public String getTimeOfMeal() {
        return TimeOfMeal;
    }


    public int getTotalCalories() {
        return TotalCalories;
    }

}
