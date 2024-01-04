package com.hwpBackend.hwpSpring.commandLineRunner;

import com.hwpBackend.hwpSpring.entity.*;
import com.hwpBackend.hwpSpring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
public class MemberJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private DietInfoRepository dietInfoRepository;
    @Autowired
    private DietRecordRepository dietRecordRepository;
    @Autowired
    private ExerciseInfoRepository exerciseInfoRepository;
    @Autowired
    private ExerciseRecordRepository exerciseRecordRepository;
    @Autowired
    private MemberRepository memberRepository;


    @Override
    public void run(String... args) throws Exception {

        // db test
        Member member1 = new Member("aaa", "123", "aaa@aa.com", "고길동", 32, "남", List.of("USER", "ADMIN"));
        Member member2 = new Member("aab", "1234", "aab@aa.com", "고길순", 33, "여", List.of("USER"));
        memberRepository.save(member1);
        memberRepository.save(member2);

        ExerciseInfo exerciseInfo1 = new ExerciseInfo("헬스", "상체", 100);
        ExerciseInfo exerciseInfo2 = new ExerciseInfo("헬스", "하체", 120);
        exerciseInfoRepository.save(exerciseInfo1);
        exerciseInfoRepository.save(exerciseInfo2);

        ExerciseRecord exerciseRecord1 = new ExerciseRecord(member1, exerciseInfo1, LocalDate.now(), 60, 50, 30, 5);
        exerciseRecordRepository.save(exerciseRecord1);

        DietInfo dietInfo1 = new DietInfo("달걀", 50, 50, 50, 50);
        dietInfoRepository.save(dietInfo1);

        DietRecord dietRecord1 = new DietRecord(member1, dietInfo1, LocalDate.now(), "점심", 50);
        dietRecordRepository.save(dietRecord1);

        System.out.println(memberRepository.findById("aaa"));

    }


}
