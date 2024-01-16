package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.dto.DietRecordDto;
import com.hwpBackend.hwpSpring.entity.DietRecord;
import com.hwpBackend.hwpSpring.exception.InfoOrRecordNotFoundException;
import com.hwpBackend.hwpSpring.repository.DietRecordRepository;
import com.hwpBackend.hwpSpring.repository.MemberRepository;
import com.hwpBackend.hwpSpring.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/dietRecords")
public class DietRecordController {
    private final DietRecordRepository repository;
    private final MemberRepository memberRepository;

    public DietRecordController(DietRecordRepository repository, MemberRepository memberRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
    }

    ;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<DietRecord> retrieveAllDietRecord() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveDietRecordById(@PathVariable(value = "id") String id) {
        String currentUser = SecurityUtil.getCurrentUsername();

        if(!currentUser.equals(id)){
            throw new AccessDeniedException("본인의 정보가 아닙니다.");
        }
        List<DietRecord> savedDietRecord = repository.findByMember_Username(id);

        if (savedDietRecord.isEmpty()) throw new InfoOrRecordNotFoundException("id:" + id.toString());
        return new ResponseEntity<>(savedDietRecord, HttpStatus.OK);
    }

    @GetMapping("/{id}/{record}")
    public ResponseEntity<?> retrieveDietRecord(@PathVariable(value = "id") String id,
                                                @PathVariable(value = "record") LocalDate record) {
        String currentUser = SecurityUtil.getCurrentUsername();

        if(!currentUser.equals(id)){
            throw new AccessDeniedException("본인의 정보가 아닙니다.");
        }
        List<DietRecord> savedDietRecord = repository.findByMember_UsernameAndRecord(id, record);

        if (savedDietRecord.isEmpty()) throw new InfoOrRecordNotFoundException("id:" + id.toString());
        return new ResponseEntity<>(savedDietRecord, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DietRecord> createDietRecord(@RequestBody DietRecordDto dietRecordDto) {
        String currentUser = SecurityUtil.getCurrentUsername();


        DietRecord dietRecord = dietRecordDto.toEntity(memberRepository.findByUsername(currentUser));
        DietRecord savedDietRecord = repository.save(dietRecord);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDietRecord.getDietRecordId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}/delete")
    public void deleteDietRecord(@PathVariable(value = "id") Integer id) {
        String currentUser = SecurityUtil.getCurrentUsername();

        DietRecord record = repository.findById(id).orElse(null);

        if (record != null && currentUser.equals(Objects.requireNonNull(record).getMember().getUsername())) {
            repository.deleteById(id);
        } else if (record == null) {
            throw new InfoOrRecordNotFoundException("해당하는 정보를 찾을 수 없습니다.");
        } else {
            // 본인의 정보가 아님
            throw new AccessDeniedException("본인의 정보가 아닙니다.");
        }
    }
}
