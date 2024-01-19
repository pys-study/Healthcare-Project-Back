package com.hwpBackend.hwpSpring.dto;

import com.hwpBackend.hwpSpring.entity.Member;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

//    DB에 회원을 직접 넣는 방식 대신 회원가입 로직을 구현해준다.
//
//    모든 객체의 생성은 Builder Pattern 사용
//    DB에 password 저장한 후 UserDeatils를 생성하며 encoding을 하는 방식 대신, DB 자체에 encoding된 password를 저장
//    Entity를 직접 사용하는 것은 매우 좋지 않은 방법 ➡︎ Dto 사용!

    private String username;
    private String email;
    private String name;
    private Integer age;
    private String gender;

    static public MemberDto toDto(Member member){
        return MemberDto.builder()
                .username(member.getUsername())
                .email(member.getEmail())
                .name(member.getName())
                .age(member.getAge())
                .gender(member.getGender()).build();
    }

    public Member toEntity(){
        return Member.builder()
                .username(username)
                .email(email)
                .name(name)
                .age(age)
                .gender(gender).build();
    }
}
