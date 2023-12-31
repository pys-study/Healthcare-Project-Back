package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.entity.DietInfo;
import com.hwpBackend.hwpSpring.exception.InfoOrRecordNotFoundException;
import com.hwpBackend.hwpSpring.repository.DietInfoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DietInfoController {

    private DietInfoRepository repository;

    public DietInfoController(DietInfoRepository repository) {
        this.repository = repository;
    }

    ;

    @GetMapping("/dietInfo")
    public List<DietInfo> retrieveAllDietInfo() {
        return repository.findAll();
    }

    @GetMapping("/dietInfo/{id}")
    public Optional<DietInfo> retrieveDietInfo(@PathVariable(value = "id") Integer id) {
        Optional<DietInfo> dietInfo = repository.findById(id);

        if (dietInfo.isEmpty()) throw new InfoOrRecordNotFoundException("id:" + id.toString());
        return dietInfo;
    }

    @PostMapping("/dietInfo")
    public ResponseEntity<DietInfo> createDietInfo(@RequestBody DietInfo dietInfo) {
        DietInfo savedDietInfo = repository.save(dietInfo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDietInfo.getDietInfoID())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/dietInfo/{id}")
    public void deleteDietInfo(@PathVariable(value = "id") Integer id) { // String인 경우 반드시 value값을 지정해줄 것
        repository.deleteById(id);
    }
}
