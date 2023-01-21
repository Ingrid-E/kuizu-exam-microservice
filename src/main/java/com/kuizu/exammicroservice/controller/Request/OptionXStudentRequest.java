package com.kuizu.exammicroservice.controller.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionXStudentRequest {
    private Long idOptionxStudent;
    private Long idStudent;
    private Long idOption;
}
