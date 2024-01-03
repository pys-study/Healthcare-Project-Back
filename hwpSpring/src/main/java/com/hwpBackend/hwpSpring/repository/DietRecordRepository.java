package com.hwpBackend.hwpSpring.repository;

import com.hwpBackend.hwpSpring.entity.DietRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRecordRepository extends JpaRepository<DietRecord, Integer> {
}
