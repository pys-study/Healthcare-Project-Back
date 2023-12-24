package com.hwpBackend.hwpSpring.commandLineRunner;

import com.hwpBackend.hwpSpring.entity.ExerciseInfo;
import com.hwpBackend.hwpSpring.entity.Member;
import com.hwpBackend.hwpSpring.repository.MemberSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MemberJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private MemberSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {

        // db test
        repository.save(new Member("aaa", "123", "aaa@aa.com", "고길동", 32, "남"));
        repository.save(new Member("aab", "1234", "aab@aa.com", "고길순", 33, "여"));
        System.out.println(repository.findById("aaa"));

//        repository.save(new ExerciseInfo(1, "하체", "중량", ""))
    }


}
