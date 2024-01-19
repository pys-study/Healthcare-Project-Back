package com.hwpBackend.hwpSpring.repository;

import com.hwpBackend.hwpSpring.entity.DietRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DietRecordRepository extends JpaRepository<DietRecord, Integer> {
    List<DietRecord> findByMember_Username(String id);

    List<DietRecord> findByMember_UsernameAndRecord(String id, LocalDate record);
}
