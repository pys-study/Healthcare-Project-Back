package com.hwpBackend.hwpSpring.dto;

import com.hwpBackend.hwpSpring.entity.Member;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

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
