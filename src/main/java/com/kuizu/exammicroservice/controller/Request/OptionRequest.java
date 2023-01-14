package com.kuizu.exammicroservice.controller.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionRequest {
    private Long idOption;
    private Long idQuestion;
    private String description;
    private String type;
    private Boolean isCorrect;
}
