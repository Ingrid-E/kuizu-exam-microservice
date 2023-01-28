package com.kuizu.exammicroservice.controller;
import com.kuizu.exammicroservice.controller.Response.GetExamResponse;
import com.kuizu.exammicroservice.service.ExamService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExamController.class)
@AutoConfigureMockMvc
class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService myService;
    @Test
    void getExams() throws Exception{

        List<GetExamResponse> myExams = Arrays.asList(
                new GetExamResponse(                8L,
                        "Cui asado",
                        "Perui",
                        LocalDateTime.of(2023,2,9,18,0,0),
                        LocalDateTime.of(2023,3,8,18,0,0),
                        20,
                        "active",
                        "63be2a568846c43f47b9da24"),
                new GetExamResponse(                10L,
                        "Ratatouille",
                        "France",
                        LocalDateTime.of(2023,9,3,16,0,0),
                        LocalDateTime.of(2023,11,8,21,0,0),
                        15,
                        "active",
                        "63be2a568846c43f47b9da26")

        );

        when(myService.getExams()).thenReturn(myExams);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Cui asado"));

    }

    @Test
    void getExam() {
    }

    @Test
    void testGetExam() {
    }

    @Test
    void createExam() {
    }

    @Test
    void updateExam() {
    }

    @Test
    void setExamStatus() {
    }

    @Test
    void deleteExam() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void listStudents() {
    }

    @Test
    void listStudentChosenOptions() {
    }
}