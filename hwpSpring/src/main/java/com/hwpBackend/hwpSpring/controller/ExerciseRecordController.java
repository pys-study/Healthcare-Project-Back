package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.dto.ExerciseRecordDto;
import com.hwpBackend.hwpSpring.entity.ExerciseRecord;
import com.hwpBackend.hwpSpring.entity.Member;
import com.hwpBackend.hwpSpring.exception.InfoOrRecordNotFoundException;
import com.hwpBackend.hwpSpring.repository.ExerciseRecordRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/exerciseRecords")
public class ExerciseRecordController {
    private final ExerciseRecordRepository repository;
    private final MemberRepository memberRepository;

    public ExerciseRecordController(ExerciseRecordRepository repository, MemberRepository memberRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
    }

    ;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<ExerciseRecord> retrieveAllExerciseRecord() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveExerciseRecordById(@PathVariable(value = "id") String id) {
        String currentUser = SecurityUtil.getCurrentUsername();

        if (!currentUser.equals(id)) {
            throw new AccessDeniedException("본인의 정보가 아닙니다.");
        }
        List<ExerciseRecord> savedExerciseRecord = repository.findByMember_Username(id);

        if (savedExerciseRecord.isEmpty()) throw new InfoOrRecordNotFoundException("id:" + id.toString());
        return new ResponseEntity<>(savedExerciseRecord, HttpStatus.OK);
    }

    @GetMapping("/{id}/{record}")
    public ResponseEntity<?> retrieveExerciseRecord(@PathVariable(value = "id") String id,
                                                    @PathVariable(value = "record") LocalDate record) {
        String currentUser = SecurityUtil.getCurrentUsername();

        if (!currentUser.equals(id)) {
            throw new AccessDeniedException("본인의 정보가 아닙니다.");
        }
        List<ExerciseRecord> savedExerciseRecord = repository.findByMember_UsernameAndRecordDate(id, record);

        if (savedExerciseRecord.isEmpty()) throw new InfoOrRecordNotFoundException("id:" + id.toString());
        return new ResponseEntity<>(savedExerciseRecord, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<ExerciseRecord> createExerciseRecord(@RequestBody ExerciseRecordDto exerciseRecordDto) {
        String currentUser = SecurityUtil.getCurrentUsername();

        if (!currentUser.equals(exerciseRecordDto.getUsername())) {
            throw new AccessDeniedException("본인의 정보만 추가할 수 있습니다.");
        }

        ExerciseRecord exerciseRecord = exerciseRecordDto.toEntity(memberRepository.findByUsername(currentUser));
        exerciseRecord.calcTotalCalories();
        ExerciseRecord savedExerciseRecord = repository.save(exerciseRecord);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedExerciseRecord.getRecordId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/delete")
    public void deleteExerciseRecord(@PathVariable(value = "id") Integer id) { // String인 경우 반드시 value값을 지정해줄 것
        String currentUser = SecurityUtil.getCurrentUsername();

        // findById 메서드의 결과가 null이면 해당 id에 해당하는 정보가 없다는 뜻
        ExerciseRecord record = repository.findById(id).orElse(null);

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
