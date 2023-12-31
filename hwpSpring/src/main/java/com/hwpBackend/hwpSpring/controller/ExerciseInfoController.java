package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.entity.ExerciseInfo;
import com.hwpBackend.hwpSpring.exception.InfoOrRecordNotFoundException;
import com.hwpBackend.hwpSpring.repository.ExerciseInfoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ExerciseInfoController {

    private ExerciseInfoRepository repository;

    public ExerciseInfoController(ExerciseInfoRepository repository) {
        this.repository = repository;
    }

    ;

    @GetMapping("/exerciseInfo")
    public List<ExerciseInfo> retrieveAllExerciseInfo() {
        return repository.findAll();
    }

    @GetMapping("/exerciseInfo/{id}")
    public Optional<ExerciseInfo> retrieveExerciseInfo(@PathVariable(value = "id") Integer id) {
        Optional<ExerciseInfo> exerciseInfo = repository.findById(id);

        if (exerciseInfo.isEmpty()) throw new InfoOrRecordNotFoundException("id:" + id.toString());
        return exerciseInfo;
    }

    @PostMapping("/exerciseInfo")
    public ResponseEntity<ExerciseInfo> createExerciseInfo(@RequestBody ExerciseInfo exerciseInfo) {
        ExerciseInfo savedExerciseInfo = repository.save(exerciseInfo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedExerciseInfo.getExerciseInfoID())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/exerciseInfo/{id}")
    public void deleteExerciseInfo(@PathVariable(value = "id") Integer id) { // String인 경우 반드시 value값을 지정해줄 것
        repository.deleteById(id);
    }
}
