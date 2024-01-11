package com.hwpBackend.hwpSpring.dto;

import com.hwpBackend.hwpSpring.entity.ExerciseInfo;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseInfoDto {

    private Integer exerciseInfoID;
    private String exerciseType;
    private String exerciseName;
    private Integer caloriesPerMinutes;

    static public ExerciseInfoDto toDto(ExerciseInfo exerciseInfo){
        return ExerciseInfoDto.builder()
                .exerciseInfoID(exerciseInfo.getExerciseInfoID())
                .exerciseType(exerciseInfo.getExerciseType())
                .exerciseName(exerciseInfo.getExerciseName())
                .caloriesPerMinutes(exerciseInfo.getCaloriesPerMinutes()).build();
    }

    public ExerciseInfo toEntity(){
        return ExerciseInfo.builder()
                .exerciseInfoID(exerciseInfoID)
                .exerciseType(exerciseType)
                .exerciseName(exerciseName)
                .caloriesPerMinutes(caloriesPerMinutes).build();
    }
}
