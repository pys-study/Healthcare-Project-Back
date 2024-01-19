package com.hwpBackend.hwpSpring.repository;

import com.hwpBackend.hwpSpring.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByRefreshToken(String token);
}
