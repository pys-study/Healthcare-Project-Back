package com.hwpBackend.hwpSpring.repository;

import com.hwpBackend.hwpSpring.entity.DietInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietInfoRepository extends JpaRepository<DietInfo, Integer> {
}
