package com.hwpBackend.hwpSpring.repository;

import com.hwpBackend.hwpSpring.entity.DietRecord;
import com.hwpBackend.hwpSpring.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietRecordRepository extends JpaRepository<DietRecord, Integer> {
    List<ExerciseRecord> findByMember_Username(String id);
}
