package com.kuizu.exammicroservice.controller;

import com.kuizu.exammicroservice.controller.Request.ExamStudentOptionsRequest;
import com.kuizu.exammicroservice.controller.Request.OptionRequest;
import com.kuizu.exammicroservice.controller.Request.OptionXStudentRequest;
import com.kuizu.exammicroservice.controller.Response.GenericResponse;
import com.kuizu.exammicroservice.controller.Response.GetGradeResponse;
import com.kuizu.exammicroservice.controller.Response.GetOptionResponse;
import com.kuizu.exammicroservice.controller.Response.GetStudentOptionResponse;
import com.kuizu.exammicroservice.controller.Response.IdResponse;
import com.kuizu.exammicroservice.service.OptionService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/exam/question/option")
@RequiredArgsConstructor
@Log
public class OptionController {
    private final OptionService optionService;

    @PostMapping
    public GenericResponse<IdResponse> createOption(@RequestBody OptionRequest optionRequest) {
        log.info(optionRequest.toString());
        return new GenericResponse<>(true, HttpStatus.CREATED, "Option Created!", optionService.createOption(optionRequest));
    }

    @GetMapping
    public GenericResponse<List<GetOptionResponse>> getQuestionOptions(@RequestParam(name="idQuestion") Long idQuestion) {
        log.info(idQuestion.toString());
        return new GenericResponse<>(true, HttpStatus.OK, "Question options", optionService.getQuestionOptions(idQuestion));
    }

    @GetMapping("/{id_option}")
    public GenericResponse<GetOptionResponse> getOption(@PathVariable("id_option") Long idOption) {
        log.info(idOption.toString());
        return new GenericResponse<>(true, HttpStatus.OK, "Option Found", optionService.getOption(idOption));
    }

    @PutMapping
    public GenericResponse<IdResponse> updateQuestion(@RequestBody OptionRequest optionRequest) {
        log.info(optionRequest.toString());
        optionService.updateQuestion(optionRequest);
        return new GenericResponse<>(true, HttpStatus.OK, "Option Updated");
    }

    @DeleteMapping("/{id_option}")
    public GenericResponse<GetOptionResponse> deleteOption(@PathVariable("id_option") Long idOption) {
        log.info(idOption.toString());
        optionService.deleteOption(idOption);
        return new GenericResponse<>(true, HttpStatus.OK, "Option Deleted");
    }

    @PostMapping("/chosen")
    public GenericResponse<IdResponse> optionChosen(@RequestBody OptionXStudentRequest optionXStudentRequest) {
        log.info(optionXStudentRequest.toString());
        return new GenericResponse<>(true, HttpStatus.CREATED, "Student set option", optionService.addOptionXStudent(optionXStudentRequest));
    }
    @DeleteMapping("/chosen")
    public GenericResponse<IdResponse> deleteOptionChosen(@RequestBody OptionXStudentRequest optionXStudentRequest) {
        log.info(optionXStudentRequest.toString());
        optionService.deleteOptionXStudent(optionXStudentRequest);
        return new GenericResponse<>(true, HttpStatus.OK, "Student set option deleted");
    }
    @GetMapping("/chosen")
    public GenericResponse<List<GetStudentOptionResponse>> listOptions(@RequestBody OptionXStudentRequest optionXStudentRequest) {
        log.info(optionXStudentRequest.getIdStudent().toString());
        optionService.deleteOptionXStudent(optionXStudentRequest);
        return new GenericResponse<>(true, HttpStatus.OK, "Student set option deleted", optionService.listStudentOptions(optionXStudentRequest.getIdStudent()));
    }

}
