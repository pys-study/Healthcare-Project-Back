package com.hwpBackend.hwpSpring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.naming.Name;
import java.time.LocalDate;

@Entity
public class ExerciseRecord {
    public ExerciseRecord() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer RecordID; // PK
    @ManyToOne(fetch = FetchType.LAZY) // 기본값인 FetchType.EAGER 사용 시 운동 세부 정보와 함께 사용자 세부 정보도 같이 가져온다
    @JsonIgnore
    private Member member; // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="exercise_info_id")
    private ExerciseInfo exerciseInfo; // FK
    private LocalDate RecordDate;
    private Integer DurationMinutes;
    private Integer Weight;
    private Integer CountPerSets;
    private Integer Sets;
    private Integer TotalCalories;


    // constructor
    public ExerciseRecord(Member member, ExerciseInfo exerciseInfo, LocalDate recordDate,
                          Integer durationMinutes, Integer weight, Integer countPerSets, Integer sets) {
        this.member = member;
        this.exerciseInfo = exerciseInfo;
        RecordDate = recordDate;
        DurationMinutes = durationMinutes;
        Weight = weight;
        CountPerSets = countPerSets;
        Sets = sets;
        TotalCalories = exerciseInfo.getCaloriesPerMinutes() * sets;
    }

    // getter setter
    public Integer getRecordID() {
        return RecordID;
    }

    public LocalDate getRecordDate() {
        return RecordDate;
    }


    public Integer getDurationMinutes() {
        return DurationMinutes;
    }


    public Integer getWeight() {
        return Weight;
    }


    public Integer getCountPerSets() {
        return CountPerSets;
    }


    public Integer getSets() {
        return Sets;
    }


    public Integer getTotalCalories() {
        return TotalCalories;
    }

    public Member getMember() {
        return member;
    }

    public ExerciseInfo getExerciseInfo() {
        return exerciseInfo;
    }

    public void setRecordID(Integer recordID) {
        RecordID = recordID;
    }

    public void setRecordDate(LocalDate recordDate) {
        RecordDate = recordDate;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        DurationMinutes = durationMinutes;
    }

    public void setWeight(Integer weight) {
        Weight = weight;
    }

    public void setCountPerSets(Integer countPerSets) {
        CountPerSets = countPerSets;
    }

    public void setSets(Integer sets) {
        Sets = sets;
    }

    public void setTotalCalories(Integer totalCalories) {
        TotalCalories = totalCalories;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setExerciseInfo(ExerciseInfo exerciseInfo) {
        this.exerciseInfo = exerciseInfo;
    }
}
