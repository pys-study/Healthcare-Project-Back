package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.repository.DietInfoRepository;
import org.springframework.web.bind.annotation.GetMapping;

public class DietInfoController {

    private DietInfoRepository repository;

    public DietInfoController( DietInfoRepository repository ){ this.repository = repository; };

    @GetMapping("/dietInfomations")
}
