package com.hwpBackend.hwpSpring.repository;

import com.hwpBackend.hwpSpring.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Integer> {
    List<ExerciseRecord> findByMember_Username(String id);
    List<ExerciseRecord> findByMember_UsernameAndRecordDate(String id, LocalDate recordDate);

}
