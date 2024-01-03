package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.CurrencyServiceConfiguration;
import com.hwpBackend.hwpSpring.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CurrencyConfigurationController {

    @Autowired
    private CurrencyServiceConfiguration configuration;

    @RequestMapping("/currency-configuration")
    public CurrencyServiceConfiguration retrieveAllMember(){
        return configuration;
    }
}
