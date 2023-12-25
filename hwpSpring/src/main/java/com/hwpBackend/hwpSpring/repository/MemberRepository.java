package com.hwpBackend.hwpSpring.repository;

import com.hwpBackend.hwpSpring.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
