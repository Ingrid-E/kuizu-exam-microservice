package com.kuizu.exammicroservice.controller.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@Builder
public class GetGradeResponse {
    private Long idGrade;
    private Long idStudent;
    private Long idExam;
    private Double value;
    private LocalDateTime createdAt;
}
