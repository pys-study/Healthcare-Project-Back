package com.hwpBackend.hwpSpring.commandLineRunner;

import com.hwpBackend.hwpSpring.entity.ExerciseInfo;
import com.hwpBackend.hwpSpring.entity.ExerciseRecord;
import com.hwpBackend.hwpSpring.entity.Member;
import com.hwpBackend.hwpSpring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

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
        Member member1 = new Member("aaa", "123", "aaa@aa.com", "고길동", 32, "남");
        Member member2 = new Member("aab", "1234", "aab@aa.com", "고길순", 33, "여");
        memberRepository.save(member1);
        memberRepository.save(member2);

        ExerciseInfo exerciseInfo1 = new ExerciseInfo(null,"헬스", "상체", 100);
        exerciseInfoRepository.save(exerciseInfo1);

        ExerciseRecord exerciseRecord1 = new ExerciseRecord(null, member1, exerciseInfo1, LocalDate.now(), 60, 50, 30, 5);
        exerciseRecordRepository.save(exerciseRecord1);

        System.out.println(memberRepository.findById("aaa"));

    }


}
