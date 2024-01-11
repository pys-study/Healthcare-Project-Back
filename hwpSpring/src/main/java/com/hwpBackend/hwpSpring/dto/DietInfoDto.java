package com.hwpBackend.hwpSpring.dto;

import com.hwpBackend.hwpSpring.entity.DietInfo;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DietInfoDto {

    private Integer dietInfoId;
    private String dietName;
    private Integer calories;
    private Integer carbohydrate;
    private Integer protein;
    private Integer fats;

    static public DietInfoDto toDto(DietInfo dietInfo){
        return DietInfoDto.builder()
                .dietInfoId(dietInfo.getDietInfoId())
                .dietName(dietInfo.getDietName())
                .calories(dietInfo.getCalories())
                .carbohydrate(dietInfo.getCarbohydrate())
                .protein(dietInfo.getProtein())
                .fats(dietInfo.getFats()).build();
    }

    public DietInfo toEntity(){
        return DietInfo.builder()
                .dietInfoId(dietInfoId)
                .dietName(dietName)
                .calories(calories)
                .carbohydrate(carbohydrate)
                .protein(protein)
                .fats(fats).build();
    }


}
