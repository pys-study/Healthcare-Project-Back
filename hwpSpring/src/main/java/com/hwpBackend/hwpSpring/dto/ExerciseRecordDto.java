package com.hwpBackend.hwpSpring.dto;

import com.hwpBackend.hwpSpring.entity.ExerciseInfo;
import com.hwpBackend.hwpSpring.entity.ExerciseRecord;
import com.hwpBackend.hwpSpring.entity.Member;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRecordDto {
    private String username;
    private ExerciseInfo exerciseInfo;
    private LocalDate recordDate;
    private Integer durationMinutes;
    private Integer weight;
    private Integer countPerSets;
    private Integer sets;
    private Integer totalCalories;

    public ExerciseRecord toEntity(Member member){
        return ExerciseRecord.builder()
                .member(member)
                .exerciseInfo(exerciseInfo)
                .recordDate(recordDate)
                .durationMinutes(durationMinutes)
                .weight(weight)
                .countPerSets(countPerSets)
                .sets(sets)
                .totalCalories(totalCalories).build();
    }
}
