package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.entity.Member;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MemberController {

    @RequestMapping("/members")
    public List<Member> retrieveAllMember(){
        return Arrays.asList(
                new Member("abc", "1234", "aaa@gmail.com", "고길동", 34, "남"),
                new Member("aa", "12345", "aab@gmail.com", "홍길동", 32, "여")
        );
    }
}
