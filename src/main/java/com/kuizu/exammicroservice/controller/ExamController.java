package com.kuizu.exammicroservice.controller;

import com.kuizu.exammicroservice.controller.Request.ExamRequest;
import com.kuizu.exammicroservice.controller.Response.ExamResponse;
import com.kuizu.exammicroservice.controller.Response.GenericResponse;
import com.kuizu.exammicroservice.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
@Log
public class ExamController {
    private final ExamService examService;
    @GetMapping
    public GenericResponse<ExamResponse> getExams(){
        log.info("GET CALLED");
        return new GenericResponse<>(true, HttpStatus.CREATED,"Exam List");
    }
    @PostMapping
    public GenericResponse<ExamResponse> createExam(@RequestBody ExamRequest exam){
        log.info(exam.toString());
        return new GenericResponse<>(true, HttpStatus.CREATED,"Exam Created!", examService.createExam(exam));
    }

}
