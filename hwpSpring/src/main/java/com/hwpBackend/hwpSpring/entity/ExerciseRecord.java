package com.hwpBackend.hwpSpring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    @Builder.Default
    private Integer recordId = 0; // PK
    @ManyToOne(fetch = FetchType.EAGER) // 기본값인 FetchType.EAGER 사용 시 운동 세부 정보와 함께 사용자 세부 정보도 같이 가져온다
    private Member member; // FK
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="exercise_info_id")
    private ExerciseInfo exerciseInfo; // FK
    private LocalDate recordDate;
    private Integer durationMinutes;
    private Integer weight;
    private Integer countPerSets;
    private Integer sets;
    private Integer totalCalories;


    // constructor
    public ExerciseRecord(Member member, ExerciseInfo exerciseInfo, LocalDate recordDate,
                          Integer durationMinutes, Integer weight, Integer countPerSets, Integer sets) {
        this.member = member;
        this.exerciseInfo = exerciseInfo;
        this.recordDate = recordDate;
        this.durationMinutes = durationMinutes;
        this.weight = weight;
        this.countPerSets = countPerSets;
        this.sets = sets;
        totalCalories = exerciseInfo.getCaloriesPerMinutes() * sets;
    }
}
