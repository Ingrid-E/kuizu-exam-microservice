package com.kuizu.exammicroservice.controller;

import com.kuizu.exammicroservice.controller.Request.ExamRequest;
import com.kuizu.exammicroservice.controller.Request.ExamStudentOptionsRequest;
import com.kuizu.exammicroservice.controller.Request.ExamXStudentRequest;
import com.kuizu.exammicroservice.controller.Response.GetExamQuestionsResults;
import com.kuizu.exammicroservice.controller.Response.GetExamResponse;
import com.kuizu.exammicroservice.controller.Response.GetStudent;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public GenericResponse<GetExamResponse> getExam(@PathVariable("id_exam") Long idExam){
        log.info(idExam.toString());
        return new GenericResponse<>(true, HttpStatus.OK,"Exam Found", examService.getExamRepository(idExam));
    }
    @GetMapping("/course/{id_course}")
    public GenericResponse<List<GetExamResponse>> getExam(@PathVariable("id_course") String idCourse){
        log.info(idCourse);
        return new GenericResponse<>(true, HttpStatus.OK,"Course Exams List", examService.getCourseExams(idCourse));
    }
    @GetMapping("/course/{id_course}/student/{id_student}")
    public GenericResponse<Double> getStudentCourseAvg(@PathVariable("id_course") String idCourse, @PathVariable("id_student") Long idStudent){
        log.info(idCourse + " " + idStudent);
        return new GenericResponse<>(true, HttpStatus.OK,"Course Exams List", examService.getCourseStudentAvg(idCourse, idStudent));
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
    @PutMapping("/{id_course}")
    public GenericResponse<IdResponse> setExamStatus(@PathVariable("id_course") String idCourse){
        log.info("Change exam status: " + LocalDateTime.now(ZoneId.of("America/Bogota")));
        examService.changeExamStatus(idCourse);
        return new GenericResponse<>(true, HttpStatus.OK,"Exams Updated!");
    }
    @DeleteMapping("/{id_exam}")
    public GenericResponse<GetExamResponse> deleteExam(@PathVariable("id_exam") Long idExam){
        log.info(idExam.toString());
        examService.deleteExam(idExam);
        return new GenericResponse<>(true, HttpStatus.OK,"Exam Deleted");
    }
    @PostMapping("/student")
    public GenericResponse<IdResponse> addStudent(@RequestBody ExamXStudentRequest examXStudentRequest) {
        log.info(examXStudentRequest.toString());
        return new GenericResponse<>(true, HttpStatus.CREATED, "Student completed test", examService.addExamXStudent(examXStudentRequest));
    }
    @DeleteMapping("/student")
    public GenericResponse<IdResponse> deleteStudent(@RequestBody ExamXStudentRequest examXStudentRequest) {
        log.info(examXStudentRequest.toString());
        examService.deleteExamXStudent(examXStudentRequest);
        return new GenericResponse<>(true, HttpStatus.OK, "Student completed test deleted");
    }
    @GetMapping("/student")
    public GenericResponse<List<GetStudent>> listStudents(@RequestBody ExamRequest examRequest) {
        log.info(examRequest.getIdExam().toString());
        return new GenericResponse<>(true, HttpStatus.OK, "Student completed test deleted", examService.listStudents(examRequest.getIdExam()));
    }
    @GetMapping("/student/response")
    public GenericResponse<GetExamQuestionsResults> listStudentChosenOptions(@RequestBody ExamStudentOptionsRequest examStudentOptionsRequest) {
        log.info(examStudentOptionsRequest.getIdExam().toString());
        return new GenericResponse<>(true, HttpStatus.OK, "Student exam status", examService.studentQuestionResults(examStudentOptionsRequest.getIdStudent(), examStudentOptionsRequest.getIdExam()));
    }
}
