package com.kuizu.exammicroservice.controller;

import com.kuizu.exammicroservice.controller.Request.GradeRequest;
import com.kuizu.exammicroservice.controller.Response.GenericResponse;
import com.kuizu.exammicroservice.controller.Response.GetGradeResponse;
import com.kuizu.exammicroservice.controller.Response.GradeAverageResponse;
import com.kuizu.exammicroservice.controller.Response.IdResponse;
import com.kuizu.exammicroservice.service.GradeService;
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
@RequestMapping("/grade")
@RequiredArgsConstructor
@Log
public class GradeController {
    private final GradeService gradeService;
    @GetMapping("/course-avg/{id_course}")
    public GenericResponse<GradeAverageResponse> getCourseAverage(@PathVariable("id_course") String idCourse){
        log.info(idCourse);
        return new GenericResponse<>(true, HttpStatus.OK,"Course Grade list", new GradeAverageResponse(gradeService.getCourseAverage(idCourse)));
    }
    @GetMapping
    public GenericResponse<List<GetGradeResponse>> getAllGrades(){
        log.info("Get all Grades");
        return new GenericResponse<>(true, HttpStatus.OK,"Grade List", gradeService.getAllGrades());
    }
    @GetMapping("/{id_grade}")
    public GenericResponse<GetGradeResponse> getGrade(@PathVariable("id_grade") Long idGrade){
        log.info(idGrade.toString());
        return new GenericResponse<>(true, HttpStatus.OK,"Grade List", gradeService.getGrade(idGrade));
    }

    @GetMapping("/student/{id_student}/exam/{id_exam}")
    public GenericResponse<Double> getStudentExamGrade(@PathVariable("id_student") Long idStudent, @PathVariable("id_exam") Long idExam){
        log.info(idStudent.toString() + " " + idExam.toString());
        return new GenericResponse<>(true, HttpStatus.OK,"Grade List", gradeService.getStudentExamGrade(idStudent, idExam));
    }

    @PostMapping
    public GenericResponse<IdResponse> addGrade(@RequestBody GradeRequest grade){
        log.info(grade.toString());
        return new GenericResponse<>(true, HttpStatus.CREATED,"Grade Created", gradeService.addGrade(grade));
    }

    @PutMapping
    public GenericResponse<IdResponse> updateGrade(@RequestBody GradeRequest grade){
        log.info(grade.toString());
        gradeService.updateGrade(grade);
        return new GenericResponse<>(true, HttpStatus.OK,"Grade Updated");
    }

    @DeleteMapping("/{id_grade}")
    public GenericResponse<IdResponse> updateGrade(@PathVariable("id_grade") Long idGrade){
        log.info(idGrade.toString());
        gradeService.deleteGrade(idGrade);
        return new GenericResponse<>(true, HttpStatus.OK,"Grade Deleted");
    }


}
