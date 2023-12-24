package com.hwpBackend.hwpSpring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class ExerciseRecord {

    @Id
    @GeneratedValue
    private int RecordID;
    @ManyToOne
    private String MemberID;
    @ManyToOne
    private int ExerciseInfoID;
    private Date RecordDate;
    private int DurationMinutes;
    private int Weight;
    private int CountPerSets;
    private int Sets;
    private int TotalCalories;

    // constructor
    public ExerciseRecord(int recordID, String memberID, int exerciseInfoID, Date recordDate,
                          int durationMinutes, int weight, int countPerSets, int sets, int totalCalories) {
        RecordID = recordID;
        MemberID = memberID;
        ExerciseInfoID = exerciseInfoID;
        RecordDate = recordDate;
        DurationMinutes = durationMinutes;
        Weight = weight;
        CountPerSets = countPerSets;
        Sets = sets;
        TotalCalories = totalCalories;
    }


    // getter setter
    public int getRecordID() {
        return RecordID;
    }


    public String getMemberID() {
        return MemberID;
    }


    public int getExerciseInfoID() {
        return ExerciseInfoID;
    }


    public Date getRecordDate() {
        return RecordDate;
    }


    public int getDurationMinutes() {
        return DurationMinutes;
    }


    public int getWeight() {
        return Weight;
    }


    public int getCountPerSets() {
        return CountPerSets;
    }


    public int getSets() {
        return Sets;
    }


    public int getTotalCalories() {
        return TotalCalories;
    }

}
