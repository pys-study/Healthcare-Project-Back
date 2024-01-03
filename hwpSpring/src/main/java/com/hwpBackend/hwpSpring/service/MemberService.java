package com.hwpBackend.hwpSpring.service;

import com.hwpBackend.hwpSpring.entity.Member;
import com.hwpBackend.hwpSpring.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MemberService {
    // JPA/Hibernate -> Database
    // MemberService -> Static list
    private MemberRepository repository;
    private static List<Member> members = new ArrayList<>();

    static {
        members.add(new Member("aaa", "123", "aaa@aa.com", "고길동", 32, "남"));
        members.add(new Member("aab", "1234", "aab@aa.com", "고길순", 33, "여"));
    }

    public List<Member> findAll(){
        return members;
    }

    public Member save(Member member) {
        members.add(member);
        return member;
    }

    public Member findOne(String id){
        // id와 일치하는 멤버를 반환
        Predicate<? super Member> predicate = member -> member.getID().equals(id);
        return members.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(String id){
        Predicate<? super Member> predicate = member -> member.getID().equals(id);
        members.removeIf(predicate);
    }
}
