package com.hwpBackend.hwpSpring.entity;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Entity
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DietInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private Integer dietInfoId;
    private String dietName;
    private Integer calories;
    private Integer carbohydrate;
    private Integer protein;
    private Integer fats;

}
