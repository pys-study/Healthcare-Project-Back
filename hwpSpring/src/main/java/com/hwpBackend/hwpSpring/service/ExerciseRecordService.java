package com.hwpBackend.hwpSpring.service;

import com.hwpBackend.hwpSpring.dto.ExerciseRecordDto;
import com.hwpBackend.hwpSpring.entity.Member;

public interface ExerciseRecordService {
    public ExerciseRecordDto showMyExerciseRecords(Member member);
    public ExerciseRecordDto addExerciseRecord(ExerciseRecordDto exerciseRecordDto);
}
