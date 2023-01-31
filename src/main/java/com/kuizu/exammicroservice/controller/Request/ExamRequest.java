package com.kuizu.exammicroservice.controller.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequest {
    private Long idExam;
    private String name;
    private String description;
    private String startAt;
    private String endAt;
    private Integer timeLimit;
    private String state;
    private String idCourse;
}
