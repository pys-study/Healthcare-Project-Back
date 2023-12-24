package com.hwpBackend.hwpSpring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class ExerciseRecord {

    @Id
    private int RecordID;
    private String MemberID;
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

    public void setRecordID(int recordID) {
        RecordID = recordID;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public int getExerciseInfoID() {
        return ExerciseInfoID;
    }

    public void setExerciseInfoID(int exerciseInfoID) {
        ExerciseInfoID = exerciseInfoID;
    }

    public Date getRecordDate() {
        return RecordDate;
    }

    public void setRecordDate(Date recordDate) {
        RecordDate = recordDate;
    }

    public int getDurationMinutes() {
        return DurationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        DurationMinutes = durationMinutes;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getCountPerSets() {
        return CountPerSets;
    }

    public void setCountPerSets(int countPerSets) {
        CountPerSets = countPerSets;
    }

    public int getSets() {
        return Sets;
    }

    public void setSets(int sets) {
        Sets = sets;
    }

    public int getTotalCalories() {
        return TotalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        TotalCalories = totalCalories;
    }
}
