package com.hwpBackend.hwpSpring.dto;

import com.hwpBackend.hwpSpring.entity.ExerciseInfo;
import com.hwpBackend.hwpSpring.entity.ExerciseRecord;
import com.hwpBackend.hwpSpring.entity.Member;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseRecordDto {

    private Integer recordId;
    private Member member;
    private ExerciseInfo exerciseInfo;
    private LocalDate recordDate;
    private Integer durationMinutes;
    private Integer weight;
    private Integer countPerSets;
    private Integer sets;
    private Integer totalCalories;

    static public ExerciseRecordDto toDto(ExerciseRecord exerciseRecord){
        return ExerciseRecordDto.builder()
                .recordId(exerciseRecord.getRecordId())
                .member(exerciseRecord.getMember())
                .exerciseInfo(exerciseRecord.getExerciseInfo())
                .recordDate(exerciseRecord.getRecordDate())
                .durationMinutes(exerciseRecord.getDurationMinutes())
                .weight(exerciseRecord.getWeight())
                .countPerSets(exerciseRecord.getCountPerSets())
                .sets(exerciseRecord.getSets())
                .totalCalories(exerciseRecord.getTotalCalories()).build();
    }

    public ExerciseRecord toEntity(){
        return ExerciseRecord.builder()
                .recordId(recordId)
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
