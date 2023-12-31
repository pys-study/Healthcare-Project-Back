package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.entity.DietRecord;
import com.hwpBackend.hwpSpring.exception.InfoOrRecordNotFoundException;
import com.hwpBackend.hwpSpring.repository.DietRecordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DietRecordController {
    private DietRecordRepository repository;

    public DietRecordController(DietRecordRepository repository) {
        this.repository = repository;
    }

    ;


    @GetMapping("/dietRecords")
    public List<DietRecord> retrieveAllDietRecord() {
        return repository.findAll();
    }

    @GetMapping("/dietRecords/{id}")
    public Optional<DietRecord> retrieveDietRecord(@PathVariable(value = "id") Integer id) {
        Optional<DietRecord> dietRecord = repository.findById(id);

        if (dietRecord.isEmpty()) throw new InfoOrRecordNotFoundException("id:" + id.toString());
        return dietRecord;
    }

    @PostMapping("/dietRecords")
    public ResponseEntity<DietRecord> createDietRecord(@RequestBody DietRecord dietRecord) {
        DietRecord savedDietRecord = repository.save(dietRecord);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDietRecord.getDietRecordID())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/dietRecords/{id}")
    public void deleteDietRecord(@PathVariable(value = "id") Integer id) { // String인 경우 반드시 value값을 지정해줄 것
        repository.deleteById(id);
    }
}
