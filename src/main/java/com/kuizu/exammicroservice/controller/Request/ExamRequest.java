package com.kuizu.exammicroservice.controller.Request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamRequest {
    private String name;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Integer timeLimit;
    private String state;
    private String id_course;
}
