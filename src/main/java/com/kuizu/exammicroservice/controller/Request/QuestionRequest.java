package com.kuizu.exammicroservice.controller.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {
    private Long idQuestion;
    private Long idExam;
    private String description;
    private Double value;
}
