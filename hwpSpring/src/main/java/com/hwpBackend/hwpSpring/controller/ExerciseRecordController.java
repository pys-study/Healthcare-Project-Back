package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.entity.ExerciseRecord;
import com.hwpBackend.hwpSpring.exception.InfoOrRecordNotFoundException;
import com.hwpBackend.hwpSpring.repository.ExerciseRecordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ExerciseRecordController {
    private ExerciseRecordRepository repository;

    public ExerciseRecordController(ExerciseRecordRepository repository) {
        this.repository = repository;
    }

    ;


    @GetMapping("/exerciseRecords")
    public List<ExerciseRecord> retrieveAllExerciseRecord() {
        return repository.findAll();
    }

    @GetMapping("/exerciseRecords/{id}")
    public Optional<ExerciseRecord> retrieveExerciseRecord(@PathVariable(value = "id") Integer id) {
        Optional<ExerciseRecord> exerciseRecord = repository.findById(id);

        if (exerciseRecord.isEmpty()) throw new InfoOrRecordNotFoundException("id:" + id.toString());
        return exerciseRecord;
    }

    @PostMapping("/exerciseRecords")
    public ResponseEntity<ExerciseRecord> createExerciseRecord(@RequestBody ExerciseRecord exerciseRecord) {
        ExerciseRecord savedExerciseRecord = repository.save(exerciseRecord);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedExerciseRecord.getRecordID())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/exerciseRecords/{id}")
    public void deleteExerciseRecord(@PathVariable(value = "id") Integer id) { // String인 경우 반드시 value값을 지정해줄 것
        repository.deleteById(id);
    }
}
