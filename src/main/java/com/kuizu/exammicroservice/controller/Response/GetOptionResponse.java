package com.kuizu.exammicroservice.controller.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetOptionResponse {
    private Long idOption;
    private Long idQuestion;
    private String description;
    private String type;
    private Boolean isCorrect;
}
