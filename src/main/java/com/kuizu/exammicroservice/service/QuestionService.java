package com.kuizu.exammicroservice.service;

import com.kuizu.exammicroservice.controller.Request.QuestionRequest;
import com.kuizu.exammicroservice.controller.Response.GetQuestionResponse;
import com.kuizu.exammicroservice.controller.Response.IdResponse;
import com.kuizu.exammicroservice.dao.Repository.QuestionRepository;
import com.kuizu.exammicroservice.entity.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    public IdResponse createQuestion(QuestionRequest questionRequest){
        QuestionEntity questionEntity = QuestionEntity.builder()
                .idExam(questionRequest.getIdExam())
                .description(questionRequest.getDescription())
                .value(questionRequest.getValue())
                .build();
        return new IdResponse(questionRepository.save(questionEntity).getIdQuestion());
    }

    public void updateQuestion(QuestionRequest questionRequest){
        QuestionEntity questionEntity = QuestionEntity.builder()
                .idQuestion(questionRequest.getIdQuestion())
                .idExam(questionRequest.getIdExam())
                .description(questionRequest.getDescription())
                .value(questionRequest.getValue())
                .build();
        questionRepository.save(questionEntity);
    }

    public List<GetQuestionResponse> getExamQuestions(Long idExam){
        return questionRepository.getExamQuestions(idExam).stream()
                .map(question -> GetQuestionResponse.builder()
                        .idQuestion(question.getIdQuestion())
                        .idExam(question.getIdExam())
                        .description(question.getDescription())
                        .value(question.getValue())
                        .build())
                .toList();
    }

    public Long numExamQuestions(Long idExam){
        return questionRepository.getExamQuestions(idExam).stream()
                .mapToLong(question -> 1L)
                .sum();
    }

    public GetQuestionResponse getQuestion(Long idQuestion){
        QuestionEntity question = questionRepository.getQuestion(idQuestion);
        return GetQuestionResponse.builder()
                .idQuestion(question.getIdQuestion())
                .idExam(question.getIdExam())
                .description(question.getDescription())
                .value(question.getValue())
                .build();
    }

    public void deleteQuestion(Long idQuestion){
        questionRepository.deleteQuestion(idQuestion);
    }



}
