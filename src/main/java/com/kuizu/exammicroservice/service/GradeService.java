package com.kuizu.exammicroservice.service;

import com.kuizu.exammicroservice.controller.Request.GradeRequest;
import com.kuizu.exammicroservice.controller.Response.GetGradeResponse;
import com.kuizu.exammicroservice.controller.Response.IdResponse;
import com.kuizu.exammicroservice.dao.Repository.ExamRepository;
import com.kuizu.exammicroservice.dao.Repository.GradeRepository;
import com.kuizu.exammicroservice.entity.ExamEntity;
import com.kuizu.exammicroservice.entity.GradeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final ExamRepository examRepository;

    public Double getCourseAverage(String idCourse){
        List<ExamEntity> examEntities = examRepository.getCourseExams(idCourse);
        return examEntities
                .stream()
                .filter(exam -> exam.getState().equals("finished"))
                .mapToDouble(exam ->
                        gradeRepository.getExamGradesAverage(exam.getIdExam())
                )
                .filter(grade -> grade != 0)
                .average()
                .orElse(0);
    }

    public List<GetGradeResponse> getAllGrades(){
        return gradeRepository.getGrades().stream()
                .map(grade ->
                    GetGradeResponse.builder()
                            .idGrade(grade.getIdGrade())
                            .idStudent(grade.getIdStudent())
                            .idExam(grade.getIdExam())
                            .value(grade.getValue())
                            .createdAt(grade.getCreatedAt())
                            .build()
                ).toList();
    }

    public GetGradeResponse getGrade(Long idGrade){
        GradeEntity grade = gradeRepository.getGrade(idGrade);
        return GetGradeResponse.builder()
                .idGrade(grade.getIdGrade())
                .idStudent(grade.getIdStudent())
                .idExam(grade.getIdExam())
                .value(grade.getValue())
                .createdAt(grade.getCreatedAt())
                .build();
    }

    public IdResponse addGrade(GradeRequest gradeRequest){
        GradeEntity gradeEntity = GradeEntity.builder()
                .idStudent(gradeRequest.getIdStudent())
                .idExam(gradeRequest.getIdExam())
                .value(gradeRequest.getValue())
                .createdAt(LocalDateTime.now())
                .build();
        return new IdResponse(gradeRepository.saveGrade(gradeEntity).getIdGrade());
    }

    public void updateGrade(GradeRequest gradeRequest){
        GradeEntity gradeEntity = GradeEntity.builder()
                .idGrade(gradeRequest.getIdGrade())
                .idStudent(gradeRequest.getIdStudent())
                .idExam(gradeRequest.getIdExam())
                .value(gradeRequest.getValue())
                .createdAt(LocalDateTime.now())
                .build();
        gradeRepository.saveGrade(gradeEntity).getIdGrade();
    }

    public void deleteGrade(Long idGrade){
        gradeRepository.deleteGrade(idGrade);
    }

}
