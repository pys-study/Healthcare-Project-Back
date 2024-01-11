package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.entity.DietRecord;
import com.hwpBackend.hwpSpring.entity.ExerciseRecord;
import com.hwpBackend.hwpSpring.exception.InfoOrRecordNotFoundException;
import com.hwpBackend.hwpSpring.repository.DietRecordRepository;
import com.hwpBackend.hwpSpring.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dietRecords")
public class DietRecordController {
    private DietRecordRepository repository;

    public DietRecordController(DietRecordRepository repository) {
        this.repository = repository;
    }

    ;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<DietRecord> retrieveAllDietRecord() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveDietRecord(@PathVariable(value = "id") String id) {
        String currentUser = SecurityUtil.getCurrentUsername();

        if(!currentUser.equals(id)){
            throw new AccessDeniedException("본인의 정보가 아닙니다.");
        }
        List<ExerciseRecord> savedDietRecord = repository.findByMember_Username(id);

        if (savedDietRecord.isEmpty()) throw new InfoOrRecordNotFoundException("id:" + id.toString());
        return new ResponseEntity<>(savedDietRecord, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DietRecord> createDietRecord(@RequestBody DietRecord dietRecord) {
        String currentUser = SecurityUtil.getCurrentUsername();

        if (!currentUser.equals(dietRecord.getMember().getName())) {
            throw new AccessDeniedException("본인의 정보만 추가할 수 있습니다.");
        }

        DietRecord savedDietRecord = repository.save(dietRecord);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDietRecord.getDietRecordId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}/delete")
    public void deleteDietRecord(@PathVariable(value = "id") Integer id) { // String인 경우 반드시 value값을 지정해줄 것
        repository.deleteById(id);
    }
}
