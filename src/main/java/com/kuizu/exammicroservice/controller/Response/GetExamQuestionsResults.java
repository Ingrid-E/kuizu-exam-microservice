package com.kuizu.exammicroservice.controller.Response;

import com.kuizu.exammicroservice.entity.ExamEntity;
import com.kuizu.exammicroservice.entity.OptionEntity;
import com.kuizu.exammicroservice.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetExamQuestionsResults {
    private Long idExam;
    private String exam;
    private HashMap<Long, Long> questions;

    public GetExamQuestionsResults(ExamEntity exam, HashMap<Long, Long> results){
        this.idExam = exam.getIdExam();
        this.exam = exam.getName();
        this.questions = results;
    }


}
