package com.kuizu.exammicroservice.service;

import com.kuizu.exammicroservice.controller.Request.ExamRequest;
import com.kuizu.exammicroservice.controller.Response.ExamResponse;
import com.kuizu.exammicroservice.dao.ExamDao;
import com.kuizu.exammicroservice.dao.Repository.ExamRepository;
import com.kuizu.exammicroservice.entity.ExamEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public ExamResponse createExam(ExamRequest examRequest){

        ExamEntity exam = ExamEntity.builder()
                .name(examRequest.getName())
                .description(examRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .startAt(LocalDateTime.parse(examRequest.getStartAt(), FORMATTER))
                .endAt(LocalDateTime.parse(examRequest.getEndAt(), FORMATTER))
                .timeLimit(examRequest.getTimeLimit())
                .state(examRequest.getState())
                .id_course(examRequest.getId_course())
                .build();

        ExamEntity examEntity = examRepository.save(exam);
        return new ExamResponse(examEntity.getId_exam());
    }
}
