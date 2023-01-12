package com.kuizu.exammicroservice.controller.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GradeAverageResponse {
    private Double average;

}
