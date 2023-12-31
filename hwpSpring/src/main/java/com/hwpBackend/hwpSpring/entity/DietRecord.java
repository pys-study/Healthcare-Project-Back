package com.hwpBackend.hwpSpring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class DietRecord {
    public DietRecord() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer DietRecordID = 0; // PK
    @ManyToOne(fetch = FetchType.EAGER) // 객체에만 사용 가능
    private Member member; // FK
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diet_info_id")
    private DietInfo dietInfo; // FK
    private LocalDate Record;
    private String TimeOfMeal;
    private Integer TotalCalories;

    // constructor


    public DietRecord(Member member, DietInfo dietInfo, LocalDate record, String timeOfMeal, Integer totalCalories) {
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

    public LocalDate getRecord() {
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

    public void setRecord(LocalDate record) {
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
