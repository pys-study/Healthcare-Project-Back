package com.hwpBackend.hwpSpring.dto;

import com.hwpBackend.hwpSpring.entity.Member;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String userID;
    private String email;
    private String username;
    private Integer age;
    private String gender;

    static public MemberDto toDto(Member member) {
        return MemberDto.builder()
                .userID(member.getUserID())
                .username(member.getUsername())
                .email(member.getEmail())
                .age(member.getAge())
                .gender(member.getGender())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .userID(userID)
                .username(username)
                .email(email)
                .age(age)
                .gender(gender)
                .build();
    }
}
