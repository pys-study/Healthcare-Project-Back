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
public class DietRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    @Builder.Default
    private Integer dietRecordId = 0; // PK
    @ManyToOne(fetch = FetchType.EAGER) // 객체에만 사용 가능
    private Member member; // FK
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diet_info_id")
    private DietInfo dietInfo; // FK
    private LocalDate record;
    private String timeOfMeal;
    private Integer totalCalories;

}
