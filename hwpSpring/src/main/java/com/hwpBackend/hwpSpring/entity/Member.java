package com.hwpBackend.hwpSpring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "userID")
public class Member implements UserDetails {
    // getter setter
    @Id
    private String userID;
    private String password;
    private String email;
    private String username;
    private Integer age;
    private String gender;

    // FK
    @OneToMany(mappedBy = "member")
    @JsonIgnore // Member Bean에 운동 리스트, 식단 리스트를 json형태로 가져오려는 것은 아니기 때문에 어노테이션 적용
    private List<ExerciseRecord> exerciseRecordList;
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<DietRecord> dietRecordList;

    public void setExerciseRecordList(List<ExerciseRecord> exerciseRecordList) {
        this.exerciseRecordList = exerciseRecordList;
    }

    public void setDietRecordList(List<DietRecord> dietRecordList) {
        this.dietRecordList = dietRecordList;
    }
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
