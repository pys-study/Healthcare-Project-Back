package com.hwpBackend.hwpSpring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class DietRecord {
    public DietRecord() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer DietRecordID = 0; // PK
    @ManyToOne(fetch = FetchType.LAZY) // 객체에만 사용 가능
    @JsonIgnore
    private Member member; // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "diet_info_id")
    private DietInfo dietInfo; // FK
    private Date Record;
    private String TimeOfMeal;
    private Integer TotalCalories;

    // constructor


    public DietRecord(Member member, DietInfo dietInfo, Date record, String timeOfMeal, Integer totalCalories) {
        this.member = member;
        this.dietInfo = dietInfo;
        Record = record;
        TimeOfMeal = timeOfMeal;
        TotalCalories = totalCalories;
    }

    // getter setter
    public Integer getDietRecordID() {
        return DietRecordID;
    }

    public Date getRecord() {
        return Record;
    }


    public String getTimeOfMeal() {
        return TimeOfMeal;
    }


    public Integer getTotalCalories() {
        return TotalCalories;
    }

    public Member getMember() {
        return member;
    }

    public DietInfo getDietInfo() {
        return dietInfo;
    }

    public void setDietRecordID(Integer dietRecordID) {
        DietRecordID = dietRecordID;
    }

    public void setRecord(Date record) {
        Record = record;
    }

    public void setTimeOfMeal(String timeOfMeal) {
        TimeOfMeal = timeOfMeal;
    }

    public void setTotalCalories(Integer totalCalories) {
        TotalCalories = totalCalories;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setDietInfo(DietInfo dietInfo) {
        this.dietInfo = dietInfo;
    }
}
