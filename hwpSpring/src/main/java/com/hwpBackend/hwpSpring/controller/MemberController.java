package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.entity.Member;
import com.hwpBackend.hwpSpring.exception.UserNotFoundException;
import com.hwpBackend.hwpSpring.repository.MemberRepository;
import com.hwpBackend.hwpSpring.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {
    private MemberRepository repository;

    public MemberController(MemberRepository repository) {
        this.repository = repository;
    }

    // GET /members
    @GetMapping("/members")
    public List<Member> retrieveAllMember() {
        return repository.findAll();
    }

    // GET /members
    @GetMapping("/members/{id}")
    public Optional<Member> retrieveMember(@PathVariable(value = "id") String id) { // String인 경우 반드시 value값을 지정해줄 것
        Optional<Member> member = repository.findById(id);

        if (member.isEmpty()) throw new UserNotFoundException("id:" + id); // 존재하지 않는 사용자 예외처리
        return member;
    }

    @GetMapping("/")
    public String mappingTest() {
        return "hello world";
    }

    // POST /members
    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {

        Member savedMember = repository.save(member);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMember.getID())
                .toUri();

        return ResponseEntity.created(location).build();

    }


    // DELETE /members
    @DeleteMapping("/members/{id}")
    public void deleteMember(@PathVariable(value = "id") String id) { // String인 경우 반드시 value값을 지정해줄 것
        repository.deleteById(id);
    }
}
