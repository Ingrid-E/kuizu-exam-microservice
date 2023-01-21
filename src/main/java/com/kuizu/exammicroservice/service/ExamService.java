package com.kuizu.exammicroservice.service;

import com.kuizu.exammicroservice.controller.Request.ExamRequest;
import com.kuizu.exammicroservice.controller.Request.ExamStudentOptionsRequest;
import com.kuizu.exammicroservice.controller.Request.ExamXStudentRequest;
import com.kuizu.exammicroservice.controller.Request.GradeRequest;
import com.kuizu.exammicroservice.controller.Request.OptionXStudentRequest;
import com.kuizu.exammicroservice.controller.Response.GetExamQuestionsResults;
import com.kuizu.exammicroservice.controller.Response.GetExamResponse;
import com.kuizu.exammicroservice.controller.Response.GetOptionResponse;
import com.kuizu.exammicroservice.controller.Response.GetQuestionResponse;
import com.kuizu.exammicroservice.controller.Response.GetStudent;
import com.kuizu.exammicroservice.controller.Response.IdResponse;
import com.kuizu.exammicroservice.dao.Repository.ExamRepository;
import com.kuizu.exammicroservice.dao.Repository.GradeRepository;
import com.kuizu.exammicroservice.entity.ExamEntity;
import com.kuizu.exammicroservice.entity.ExamXStudentEntity;
import com.kuizu.exammicroservice.entity.GradeEntity;
import com.kuizu.exammicroservice.entity.OptionEntity;
import com.kuizu.exammicroservice.entity.OptionxStudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

    private final GradeRepository gradeRepository;
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
        if(examRequest.getState().equals("finished")){
            setExamGrades(examRequest.getIdExam());
        }
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

    public void changeExamStatus(String courseId){
        examRepository.getCourseExams(courseId).stream()
                .forEach(exam -> {
                    LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Bogota"));
                    if(now.isAfter(exam.getEndAt())){
                        exam.setState("finished");
                    }else if(now.isAfter(exam.getStartAt())){
                        exam.setState("active");
                    }else{
                        exam.setState("inactive");
                    }
                    this.examRepository.save(exam);
                });
    }

    public void setExamGrades(Long idExam){
        List<ExamXStudentEntity> students = examRepository.listStudents(idExam);
        for (ExamXStudentEntity student: students) {
            HashMap<Long, Long> studentExamResponse = studentQuestionOption(student.getIdStudent(), idExam);
            Double questionValue = 5.0/studentExamResponse.size();
            Double correct = studentExamResponse.entrySet()
                    .stream()
                    .filter(entrySet -> {
                        GetOptionResponse option = optionService.getOption(entrySet.getValue());
                        if(option != null && option.getIsCorrect()){
                            return true;
                        }
                        return false;
                    })
                    .mapToDouble(correctOption -> 1.0)
                    .sum();

            gradeRepository.saveGrade(new GradeEntity(null, student.getIdStudent(), idExam, correct*questionValue, LocalDateTime.now()));

        }
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
        examRepository.deleteExamXStudent(examXStudentRequest.getIdStudent(), examXStudentRequest.getIdExam());
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
        ExamEntity exam = examRepository.getExams(idExam);
        return new GetExamQuestionsResults(exam, studentQuestionOption(idStudent, idExam));
    }

    public HashMap<Long, Long> studentQuestionOption(Long idStudent, Long idExam){
        HashMap<Long, Long> studentExamResponse = new HashMap<>();
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
        return studentExamResponse;
    }
}
