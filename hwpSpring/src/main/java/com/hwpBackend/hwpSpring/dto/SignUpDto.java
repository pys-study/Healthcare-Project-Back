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

//    회원가입 로직
//    1. SignUpDto로 들어온 정보를 toEntity 메서드를 통해 Member 엔티티로 변환한다.
//    2. 변환한 엔티티를 DB에 저장한다. 이때, 반환값으로 저장된 Member 엔티티를 받는다.
//    3. 반환받은 엔티티를 MemberDto의 static method인 toDto를 호출하여 MemberDto로 변환하여 반환한다.

    private String username;
    private String password;
    private String email;
    private String name;
    private Integer age;
    private String gender;
    private List<String> roles = new ArrayList<>();

    public Member toEntity(String encodedPassword, List<String> roles){
        return Member.builder()
                .username(username)
                .password(encodedPassword)
                .email(email)
                .name(name)
                .age(age)
                .gender(gender)
                .roles(roles)
                .build();
    }

}
