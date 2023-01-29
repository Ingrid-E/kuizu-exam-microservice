package com.kuizu.exammicroservice.controller;

import com.kuizu.exammicroservice.controller.Request.QuestionRequest;
import com.kuizu.exammicroservice.controller.Response.GenericResponse;
import com.kuizu.exammicroservice.controller.Response.GetQuestionResponse;
import com.kuizu.exammicroservice.controller.Response.IdResponse;
import com.kuizu.exammicroservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/exam/question")
@RequiredArgsConstructor
@Log
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse<IdResponse> createQuestion(@RequestBody QuestionRequest  question){
        log.info(question.toString());
        return new GenericResponse<>(true, HttpStatus.CREATED,"Question Created!", questionService.createQuestion(question));
    }

    @GetMapping("/list/{id_exam}")
    public GenericResponse<List<GetQuestionResponse>> getExamQuestions(@PathVariable("id_exam") Long idExam){
        return new GenericResponse<>(true, HttpStatus.OK,"Exam Questions!", questionService.getExamQuestions(idExam));
    }

    @GetMapping("/{id_question}")
    public GenericResponse<GetQuestionResponse> getQuestion(@PathVariable("id_question") Long idQuestion){
        log.info(idQuestion.toString());
        return new GenericResponse<>(true, HttpStatus.OK,"Question Found", questionService.getQuestion(idQuestion));
    }

    @DeleteMapping("/{id_question}")
    public GenericResponse<GetQuestionResponse> deleteQuestion(@PathVariable("id_question") Long idQuestion){
        log.info(idQuestion.toString());
        questionService.deleteQuestion(idQuestion);
        return new GenericResponse<>(true, HttpStatus.OK,"Question Deleted");
    }

    @PutMapping
    public GenericResponse<IdResponse> updateQuestion(@RequestBody QuestionRequest  question){
        log.info(question.toString());
        questionService.updateQuestion(question);
        return new GenericResponse<>(true, HttpStatus.OK,"Question Updated!");
    }


}
