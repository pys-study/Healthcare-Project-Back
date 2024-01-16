package com.hwpBackend.hwpSpring.repository;

import com.hwpBackend.hwpSpring.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByUsername(String username);
    boolean existsByUsername(String username);
}
