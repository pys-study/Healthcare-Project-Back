package com.hwpBackend.hwpSpring.dto;

import com.hwpBackend.hwpSpring.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {

    private String userID;
    private String password;
    private String email;
    private String username;
    private Integer age;
    private String gender;
    private List<String> roles;

    public Member toEntity(String encodedPassword, List<String> roles) {

        return Member.builder()
                .userID(userID)
                .password(encodedPassword)
                .email(email)
                .username(username)
                .age(age)
                .gender(gender)
                .roles(roles)
                .build();
    }
}
