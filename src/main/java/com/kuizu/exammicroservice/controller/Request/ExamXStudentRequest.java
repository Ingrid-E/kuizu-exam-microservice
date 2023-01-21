package com.kuizu.exammicroservice.controller.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamXStudentRequest {
    private Long idExamxStudent;
    private Long idStudent;
    private Long idExam;
}
