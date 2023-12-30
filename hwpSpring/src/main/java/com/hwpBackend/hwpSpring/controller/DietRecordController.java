package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.repository.DietRecordRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DietRecordController {
    private DietRecordRepository repository;

    public DietRecordController( DietRecordRepository repository ){ this.repository = repository; };
}
