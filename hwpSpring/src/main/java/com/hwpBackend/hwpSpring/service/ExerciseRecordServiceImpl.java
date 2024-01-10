package com.hwpBackend.hwpSpring.service;

import com.hwpBackend.hwpSpring.dto.ExerciseRecordDto;
import com.hwpBackend.hwpSpring.entity.Member;
import com.hwpBackend.hwpSpring.repository.ExerciseRecordRepository;
import com.hwpBackend.hwpSpring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ExerciseRecordServiceImpl implements ExerciseRecordService{

    private final ExerciseRecordRepository exerciseRecordRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public ExerciseRecordDto showMyExerciseRecords(Member member) {
        return null;
    }

    @Transactional
    @Override
    public ExerciseRecordDto addExerciseRecord(ExerciseRecordDto exerciseRecordDto) {
        return ExerciseRecordDto.toDto(exerciseRecordRepository
                .save(exerciseRecordDto.toEntity()));
    }

}
