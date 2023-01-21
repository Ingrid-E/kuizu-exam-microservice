package com.kuizu.exammicroservice.controller.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamStudentOptionsRequest {
    private Long idExam;
    private Long idStudent;
}
