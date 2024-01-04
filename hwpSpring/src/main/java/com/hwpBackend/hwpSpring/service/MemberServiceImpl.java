package com.hwpBackend.hwpSpring.service;

import com.hwpBackend.hwpSpring.dto.JwtToken;
import com.hwpBackend.hwpSpring.dto.MemberDto;
import com.hwpBackend.hwpSpring.dto.SignUpDto;
import com.hwpBackend.hwpSpring.entity.Member;
import com.hwpBackend.hwpSpring.jwt.JwtTokenProvider;
import com.hwpBackend.hwpSpring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService{
    // JPA/Hibernate -> Database
    // MemberService -> Static list
    private static List<Member> members = new ArrayList<>();

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    public List<Member> findAll(){
        return members;
    }

    public Member save(Member member) {
        members.add(member);
        return member;
    }

    public Member findOne(String id){
        // id와 일치하는 멤버를 반환
        Predicate<? super Member> predicate = member -> member.getUserID().equals(id);
        return members.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(String id){
        Predicate<? super Member> predicate = member -> member.getUserID().equals(id);
        members.removeIf(predicate);
    }

    @Transactional
    public JwtToken signIn(String id, String password) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }

    @Transactional
    @Override
    public MemberDto signUp(SignUpDto signUpDto) {
        if (memberRepository.existsByUserID(signUpDto.getUserID())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 아이디입니다.");
        }
        // Password 암호화
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        List<String> roles = new ArrayList<>();
        roles.add("USER");  // USER 권한 부여
        return MemberDto.toDto(memberRepository.save(signUpDto.toEntity(encodedPassword, roles)));
    }
}
