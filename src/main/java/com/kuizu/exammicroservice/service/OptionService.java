package com.kuizu.exammicroservice.service;

import com.kuizu.exammicroservice.controller.Request.ExamStudentOptionsRequest;
import com.kuizu.exammicroservice.controller.Request.OptionRequest;
import com.kuizu.exammicroservice.controller.Request.OptionXStudentRequest;
import com.kuizu.exammicroservice.controller.Response.GetOptionResponse;
import com.kuizu.exammicroservice.controller.Response.GetStudentOptionResponse;
import com.kuizu.exammicroservice.controller.Response.IdResponse;
import com.kuizu.exammicroservice.dao.Repository.OptionRepository;
import com.kuizu.exammicroservice.entity.ExamXStudentEntity;
import com.kuizu.exammicroservice.entity.OptionEntity;
import com.kuizu.exammicroservice.entity.OptionxStudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;

    public IdResponse createOption(OptionRequest optionRequest){
        OptionEntity optionEntity = OptionEntity.builder()
                .idQuestion(optionRequest.getIdQuestion())
                .description(optionRequest.getDescription())
                .type(optionRequest.getType())
                .isCorrect(optionRequest.getIsCorrect())
                .build();
        return new IdResponse(optionRepository.save(optionEntity).getIdOption());
    }

    public void updateQuestion(OptionRequest optionRequest){
        OptionEntity optionEntity = OptionEntity.builder()
                .idOption(optionRequest.getIdOption())
                .idQuestion(optionRequest.getIdQuestion())
                .description(optionRequest.getDescription())
                .type(optionRequest.getType())
                .isCorrect(optionRequest.getIsCorrect())
                .build();
        optionRepository.save(optionEntity);
    }

    public List<GetOptionResponse> getQuestionOptions(Long idQuestion){
        return optionRepository.getQuestionOptions(idQuestion).stream()
                .map(option-> GetOptionResponse.builder()
                        .idOption(option.getIdOption())
                        .idQuestion(option.getIdQuestion())
                        .description(option.getDescription())
                        .type(option.getType())
                        .isCorrect(option.getIsCorrect())
                        .build())
                .toList();
    }

    public GetOptionResponse getOption(Long idOption){
        OptionEntity option = optionRepository.getOption(idOption);
        return GetOptionResponse.builder()
                .idOption(option.getIdOption())
                .idQuestion(option.getIdQuestion())
                .description(option.getDescription())
                .type(option.getType())
                .isCorrect(option.getIsCorrect())
                .build();
    }
    public void deleteOption(Long idOption){
        optionRepository.deleteOption(idOption);
    }

    public IdResponse addOptionXStudent(OptionXStudentRequest optionXStudentRequest){
        OptionxStudentEntity optionxStudent = OptionxStudentEntity.builder()
                .idOption(optionXStudentRequest.getIdOption())
                .idStudent(optionXStudentRequest.getIdStudent())
                .build();
        return new IdResponse(optionRepository.saveOptionXStudent(optionxStudent).getIdOption());
    }

    public void deleteOptionXStudent(OptionXStudentRequest optionXStudentRequest){
        OptionxStudentEntity optionxStudent = OptionxStudentEntity.builder()
                .idOption(optionXStudentRequest.getIdOption())
                .idStudent(optionXStudentRequest.getIdStudent())
                .build();
        optionRepository.deleteOptionXStudent(optionxStudent);
    }

    public List<GetStudentOptionResponse> listStudentOptions(Long idStudent){

        return optionRepository.listStudentChoices(idStudent).stream()
                .map(option-> GetStudentOptionResponse.builder()
                        .idOptionxStudent(option.getIdOptionxStudent())
                        .idStudent(option.getIdStudent())
                        .idOption(option.getIdOption())
                        .build())
                .toList();
    }

    public OptionxStudentEntity findOptionXStudent(Long idStudent, Long idOption){
        return optionRepository.findOptionXStudent(idStudent, idOption);
    }
}
