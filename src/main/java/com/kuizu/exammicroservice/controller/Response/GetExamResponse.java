package com.kuizu.exammicroservice.controller.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class GetExamResponse {
    private Integer idExam;
    private String name;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Integer timeLimit;
    private String state;
    private String idCourse;
}
