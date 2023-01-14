package com.kuizu.exammicroservice.controller.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetStudentOptionResponse {
    private Long idOptionxStudent;
    private Long idStudent;
    private Long idOption;
}
