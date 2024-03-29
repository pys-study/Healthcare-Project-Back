package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.entity.DietInfo;
import com.hwpBackend.hwpSpring.exception.InfoOrRecordNotFoundException;
import com.hwpBackend.hwpSpring.repository.DietInfoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dietInfo")
public class DietInfoController {

    private DietInfoRepository repository;

    public DietInfoController(DietInfoRepository repository) {
        this.repository = repository;
    }

    ;
    @GetMapping("/")
    public List<DietInfo> retrieveAllDietInfo() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('USER') and #username == authentication.name")
    public Optional<DietInfo> retrieveDietInfo(@PathVariable(value = "id") Integer id) {
        Optional<DietInfo> dietInfo = repository.findById(id);

        if (dietInfo.isEmpty()) throw new InfoOrRecordNotFoundException("id:" + id.toString());
        return dietInfo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<DietInfo> createDietInfo(@RequestBody DietInfo dietInfo) {
        DietInfo savedDietInfo = repository.save(dietInfo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDietInfo.getDietInfoId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public void deleteDietInfo(@PathVariable(value = "id") Integer id) { // String인 경우 반드시 value값을 지정해줄 것
        repository.deleteById(id);
    }
}
