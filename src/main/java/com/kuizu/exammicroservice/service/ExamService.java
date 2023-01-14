package com.kuizu.exammicroservice.service;

import com.kuizu.exammicroservice.controller.Request.ExamRequest;
import com.kuizu.exammicroservice.controller.Request.ExamStudentOptionsRequest;
import com.kuizu.exammicroservice.controller.Request.ExamXStudentRequest;
import com.kuizu.exammicroservice.controller.Request.OptionXStudentRequest;
import com.kuizu.exammicroservice.controller.Response.GetExamQuestionsResults;
import com.kuizu.exammicroservice.controller.Response.GetExamResponse;
import com.kuizu.exammicroservice.controller.Response.GetOptionResponse;
import com.kuizu.exammicroservice.controller.Response.GetQuestionResponse;
import com.kuizu.exammicroservice.controller.Response.GetStudent;
import com.kuizu.exammicroservice.controller.Response.IdResponse;
import com.kuizu.exammicroservice.dao.Repository.ExamRepository;
import com.kuizu.exammicroservice.entity.ExamEntity;
import com.kuizu.exammicroservice.entity.ExamXStudentEntity;
import com.kuizu.exammicroservice.entity.OptionEntity;
import com.kuizu.exammicroservice.entity.OptionxStudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ExamRepository examRepository;

    private final QuestionService questionService;

    private final OptionService optionService;

    public IdResponse createExam(ExamRequest examRequest){

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

        ExamEntity examEntity = this.examRepository.save(exam);
        return new IdResponse(examEntity.getIdExam());
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
        this.examRepository.save(exam);
    }

    public void deleteExam(Long idExam){
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

    public List<GetExamResponse> getCourseExams(String courseId){
        return examRepository.getCourseExams(courseId).stream()
                .map(exam ->
                     GetExamResponse.builder()
                            .idExam(exam.getIdExam())
                            .name(exam.getName())
                            .description(exam.getDescription())
                            .startAt(exam.getStartAt())
                            .endAt(exam.getEndAt())
                            .timeLimit(exam.getTimeLimit())
                            .state(exam.getState())
                            .idCourse(exam.getIdCourse())
                            .build()
                ).toList();
    }

    public GetExamResponse getExamRepository(Long idExam){
        ExamEntity exam = this.examRepository.getExams(idExam);
        return GetExamResponse.builder()
                .idExam(exam.getIdExam())
                .name(exam.getName())
                .description(exam.getDescription())
                .startAt(exam.getStartAt())
                .endAt(exam.getEndAt())
                .timeLimit(exam.getTimeLimit())
                .state(exam.getState())
                .idCourse(exam.getIdCourse())
                .build();
    }

    public IdResponse addExamXStudent(ExamXStudentRequest examXStudentRequest){
        ExamXStudentEntity examXStudent = ExamXStudentEntity.builder()
                .idStudent(examXStudentRequest.getIdStudent())
                .idExam(examXStudentRequest.getIdExam())
                .completedAt(LocalDateTime.now())
                .build();
        return new IdResponse(examRepository.saveExamXStudent(examXStudent).getIdStudent());
    }

    public void deleteExamXStudent(ExamXStudentRequest examXStudentRequest){
        ExamXStudentEntity examXStudent = ExamXStudentEntity.builder()
                .idStudent(examXStudentRequest.getIdStudent())
                .idExam(examXStudentRequest.getIdExam())
                .completedAt(LocalDateTime.now())
                .build();
        examRepository.deleteExamXStudent(examXStudent);
    }

    public List<GetStudent> listStudents(Long idExam){
        return examRepository.listStudents(idExam).stream()
                .map(student -> GetStudent.builder()
                        .idStudent(student.getIdStudent())
                        .completed_at(student.getCompletedAt())
                        .build())
                .toList();
    }

    public GetExamQuestionsResults studentQuestionResults(Long idStudent, Long idExam){
        HashMap<Long, Long> studentExamResponse = new HashMap<>();
        ExamEntity exam = examRepository.getExams(idExam);
        for (GetQuestionResponse question: questionService.getExamQuestions(idExam)) {
            GetOptionResponse questionOption = optionService.getQuestionOptions(question.getIdQuestion()).stream()
                    .filter(option-> optionService.findOptionXStudent(idStudent, option.getIdOption()) != null)
                    .findFirst()
                    .orElse(null);
            Long idOption = null;
            if(questionOption != null){
                idOption = questionOption.getIdOption();
            }
            studentExamResponse.put(question.getIdQuestion(), idOption);
        }

        return new GetExamQuestionsResults(exam, studentExamResponse);
    }
}
