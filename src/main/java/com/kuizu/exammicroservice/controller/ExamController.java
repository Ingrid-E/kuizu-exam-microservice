package com.kuizu.exammicroservice.controller;

import com.kuizu.exammicroservice.controller.Request.ExamRequest;
import com.kuizu.exammicroservice.controller.Response.GetExamResponse;
import com.kuizu.exammicroservice.controller.Response.IdResponse;
import com.kuizu.exammicroservice.controller.Response.GenericResponse;
import com.kuizu.exammicroservice.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
@Log
public class ExamController {
    private final ExamService examService;
    @GetMapping
    public GenericResponse<List<GetExamResponse>> getExams(){
        log.info("GET CALLED");
        return new GenericResponse<>(true, HttpStatus.OK,"Exam List", examService.getExams());
    }
    @GetMapping("/{id_exam}")
    public GenericResponse<GetExamResponse> getExam(@PathVariable("id_exam") Integer idExam){
        log.info(idExam.toString());
        return new GenericResponse<>(true, HttpStatus.OK,"Exam Found", examService.getExam(idExam));
    }
    @GetMapping("/course/{id_course}")
    public GenericResponse<List<GetExamResponse>> getExam(@PathVariable("id_course") String idCourse){
        log.info(idCourse.toString());
        return new GenericResponse<>(true, HttpStatus.OK,"Exam Found", examService.getCourseExams(idCourse));
    }
    @PostMapping
    public GenericResponse<IdResponse> createExam(@RequestBody ExamRequest exam){
        log.info(exam.toString());
        return new GenericResponse<>(true, HttpStatus.CREATED,"Exam Created!", examService.createExam(exam));
    }
    @PutMapping
    public GenericResponse<IdResponse> updateExam(@RequestBody ExamRequest exam){
        log.info(exam.toString());
        examService.updateExam(exam);
        return new GenericResponse<>(true, HttpStatus.OK,"Exam Updated!");
    }
    @DeleteMapping("/{id_course}")
    public GenericResponse<GetExamResponse> deleteExam(@PathVariable("id_course") Integer idExam){
        log.info(idExam.toString());
        examService.deleteExam(idExam);
        return new GenericResponse<>(true, HttpStatus.OK,"Exam Found");
    }
}
