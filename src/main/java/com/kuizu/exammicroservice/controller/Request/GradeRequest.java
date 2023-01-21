package com.kuizu.exammicroservice.controller.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeRequest {
    private Long idGrade;
    private Long idStudent;
    private Long idExam;
    private Double value;
}
