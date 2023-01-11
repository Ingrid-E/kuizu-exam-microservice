package com.kuizu.exammicroservice.service;

import com.kuizu.exammicroservice.controller.Request.ExamRequest;
import com.kuizu.exammicroservice.controller.Response.GetExamResponse;
import com.kuizu.exammicroservice.controller.Response.IdExamResponse;
import com.kuizu.exammicroservice.dao.Repository.ExamRepository;
import com.kuizu.exammicroservice.entity.ExamEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public IdExamResponse createExam(ExamRequest examRequest){

        ExamEntity exam = ExamEntity.builder()
                .name(examRequest.getName())
                .description(examRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .startAt(LocalDateTime.parse(examRequest.getStartAt(), FORMATTER))
                .endAt(LocalDateTime.parse(examRequest.getEndAt(), FORMATTER))
                .timeLimit(examRequest.getTimeLimit())
                .state(examRequest.getState())
                .idCourse(examRequest.getIdCourse())
                .build();

        ExamEntity examEntity = examRepository.save(exam);
        return new IdExamResponse(examEntity.getIdExam());
    }

    public void updateExam(ExamRequest examRequest){
        ExamEntity exam = ExamEntity.builder()
                .idExam(examRequest.getIdExam())
                .name(examRequest.getName())
                .description(examRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .startAt(LocalDateTime.parse(examRequest.getStartAt(), FORMATTER))
                .endAt(LocalDateTime.parse(examRequest.getEndAt(), FORMATTER))
                .timeLimit(examRequest.getTimeLimit())
                .state(examRequest.getState())
                .idCourse(examRequest.getIdCourse())
                .build();
        examRepository.save(exam);
    }

    public void deleteExam(Integer idExam){
        examRepository.deleteExam(idExam);
    }

    public List<GetExamResponse> getExams(){
        List<ExamEntity> examEntities = examRepository.getExams();
        List<GetExamResponse> getExamResponses = new ArrayList<>();
        for (ExamEntity exam : examEntities) {
            GetExamResponse getExamResponse = GetExamResponse.builder()
                    .idExam(exam.getIdExam())
                    .name(exam.getName())
                    .description(exam.getDescription())
                    .startAt(exam.getStartAt())
                    .endAt(exam.getEndAt())
                    .timeLimit(exam.getTimeLimit())
                    .state(exam.getState())
                    .idCourse(exam.getIdCourse())
                    .build();
            getExamResponses.add(getExamResponse);
        }
        return getExamResponses;
    }

    public GetExamResponse getExam(Integer idExam){
        ExamEntity exam = examRepository.getExams(idExam);
        GetExamResponse getExamResponse = GetExamResponse.builder()
                .idExam(exam.getIdExam())
                .name(exam.getName())
                .description(exam.getDescription())
                .startAt(exam.getStartAt())
                .endAt(exam.getEndAt())
                .timeLimit(exam.getTimeLimit())
                .state(exam.getState())
                .idCourse(exam.getIdCourse())
                .build();
        return getExamResponse;
    }
}
