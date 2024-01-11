package com.hwpBackend.hwpSpring.dto;

import com.hwpBackend.hwpSpring.entity.DietInfo;
import com.hwpBackend.hwpSpring.entity.DietRecord;
import com.hwpBackend.hwpSpring.entity.Member;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DietRecordDto {

    private Integer dietRecordId;
    private Member member;
    private DietInfo dietInfo;
    private LocalDate record;
    private String timeOfMeal;
    private Integer totalCalories;

    static public DietRecordDto toDto(DietRecord dietRecord){
        return DietRecordDto.builder()
                .dietRecordId(dietRecord.getDietRecordId())
                .member(dietRecord.getMember())
                .dietInfo(dietRecord.getDietInfo())
                .record(dietRecord.getRecord())
                .timeOfMeal(dietRecord.getTimeOfMeal())
                .totalCalories(dietRecord.getTotalCalories()).build();
    }

    public DietRecord toEntity(){
        return DietRecord.builder()
                .dietRecordId(dietRecordId)
                .member(member)
                .dietInfo(dietInfo)
                .record(record)
                .timeOfMeal(timeOfMeal)
                .totalCalories(totalCalories).build();
    }
}
