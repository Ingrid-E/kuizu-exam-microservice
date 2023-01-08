package com.kuizu.exammicroservice.controller;

import com.kuizu.exammicroservice.controller.Request.ExamRequest;
import com.kuizu.exammicroservice.controller.Response.ExamResponse;
import com.kuizu.exammicroservice.controller.Response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/exam")
public class ExamController {
    @PostMapping()
    public GenericResponse<ExamResponse> createExam(@RequestBody ExamRequest exam){

        return new GenericResponse<>(true, HttpStatus.CREATED,"Exam Created!");
    }

}
