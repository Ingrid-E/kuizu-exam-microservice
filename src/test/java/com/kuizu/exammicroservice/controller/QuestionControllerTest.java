package com.kuizu.exammicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuizu.exammicroservice.controller.Request.QuestionRequest;
import com.kuizu.exammicroservice.controller.Response.GetExamResponse;
import com.kuizu.exammicroservice.controller.Response.GetQuestionResponse;
import com.kuizu.exammicroservice.controller.Response.IdResponse;
import com.kuizu.exammicroservice.entity.QuestionEntity;
import com.kuizu.exammicroservice.service.ExamService;
import com.kuizu.exammicroservice.service.QuestionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Test
    void createQuestion() throws Exception{
        QuestionRequest questionRequest = new QuestionRequest(67L,7L,"HeartStrings",4.7);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(questionRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/exam/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(questionService, times(1)).createQuestion(questionRequest);
    }

    @Test
    void getExamQuestions() throws Exception{

        List<GetQuestionResponse> myExams = Arrays.asList(
                new GetQuestionResponse(14L,17L,"Strawberry",1.3),
                new GetQuestionResponse(16L,17L,"Pear",1.5)
        );

        when(questionService.getExamQuestions(1L)).thenReturn(myExams);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/question/list/17"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(questionService, times(1)).getExamQuestions(17L);


    }

    @Test
    void getOneQuestion() throws Exception{

        GetQuestionResponse myExam = new GetQuestionResponse(14L,1L,"Strawberry",1.3);

        when(questionService.getQuestion(1L)).thenReturn(myExam);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/question/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(questionService, times(1)).getQuestion(1L);

    }

    @Test
    void deleteQuestion() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/exam/question/4"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(questionService, times(1)).deleteQuestion(4L);
    }

    @Test
    void updateQuestion() throws Exception{

        QuestionRequest questionRequest = new QuestionRequest(67L,7L,"HeartStrings",4.7);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(questionRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/exam/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(questionService, times(1)).updateQuestion(questionRequest);
    }
}