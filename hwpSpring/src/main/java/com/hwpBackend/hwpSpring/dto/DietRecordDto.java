package com.hwpBackend.hwpSpring.dto;

import com.hwpBackend.hwpSpring.entity.DietInfo;
import com.hwpBackend.hwpSpring.entity.DietRecord;
import com.hwpBackend.hwpSpring.entity.Member;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DietRecordDto {

    private DietInfo dietInfo;
    private LocalDate record;
    private String timeOfMeal;
    private Integer totalCalories;

    public DietRecord toEntity(Member member){
        return DietRecord.builder()
                .member(member)
                .dietInfo(dietInfo)
                .record(record)
                .timeOfMeal(timeOfMeal)
                .totalCalories(totalCalories).build();
    }

}
