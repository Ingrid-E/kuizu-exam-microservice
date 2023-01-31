package com.kuizu.exammicroservice.controller.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetQuestionResponse {
    private Long idQuestion;
    private Long idExam;
    private String description;
    private Double value;
}
