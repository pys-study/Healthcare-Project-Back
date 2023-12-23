package com.hwpBackend.hwpSpring.commandLineRunner;

import com.hwpBackend.hwpSpring.repository.MemberJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MemberJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private MemberJdbcRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert();
    }
}
