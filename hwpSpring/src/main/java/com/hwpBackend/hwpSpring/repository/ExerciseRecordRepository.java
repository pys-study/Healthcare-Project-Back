package com.hwpBackend.hwpSpring.repository;

import com.hwpBackend.hwpSpring.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Integer> {
}
