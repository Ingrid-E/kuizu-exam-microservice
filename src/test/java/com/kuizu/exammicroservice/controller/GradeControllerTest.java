package com.kuizu.exammicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuizu.exammicroservice.controller.Request.GradeRequest;
import com.kuizu.exammicroservice.controller.Response.GenericResponse;
import com.kuizu.exammicroservice.controller.Response.GetExamResponse;
import com.kuizu.exammicroservice.controller.Response.GetGradeResponse;
import com.kuizu.exammicroservice.controller.Response.GradeAverageResponse;
import com.kuizu.exammicroservice.service.ExamService;
import com.kuizu.exammicroservice.service.GradeService;
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
@WebMvcTest(GradeController.class)
class GradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradeService gradeService;
    @MockBean
    private ExamService examService;
    @Test
    void getCourseAverage() throws Exception{
        List<GetExamResponse> grade_average_response = Arrays.asList(
                new GetExamResponse(                8L,
                        "Reposteria",
                        "Pastel de genovesa",
                        LocalDateTime.of(2023,2,9,18,0,0),
                        LocalDateTime.of(2023,3,8,18,0,0),
                        5,
                        "active",
                        "R3DULC3"),
                new GetExamResponse(                10L,
                        "Bebidas",
                        "Cafe moca",
                        LocalDateTime.of(2023,9,3,16,0,0),
                        LocalDateTime.of(2023,11,8,21,0,0),
                        5,
                        "active",
                        "R3DULC3")
        );

        when(examService.getCourseExams("R3DULC3")).thenReturn(grade_average_response);

        mockMvc.perform(MockMvcRequestBuilders.get("/grade/course-avg/R3DULC3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data.size()", Matchers.is(1)));

        verify(gradeService, times(1)).getCourseAverage("R3DULC3");
    }

    @Test
    void getAllGrades() throws Exception{
        List<GetGradeResponse> getGradeResponse = Arrays.asList(
            new GetGradeResponse(1L,6L,1L,3.6,
                    LocalDateTime.of(2023,2,9,18,0,0)),
            new GetGradeResponse(2L,6L,2L,2.7,
                    LocalDateTime.of(2023,3,19,18,0,0))
        );

        when(gradeService.getAllGrades()).thenReturn(getGradeResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/grade"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data.size()", Matchers.is(2)));

        verify(gradeService, times(1)).getAllGrades();

    }

    @Test
    void getGrade() throws Exception{
        GetGradeResponse getGradeResponse = new GetGradeResponse(3L,8L,2L,4.8,
                LocalDateTime.of(2023,2,9,18,0,0));

        when(gradeService.getGrade(3L)).thenReturn(getGradeResponse);
        mockMvc.perform(MockMvcRequestBuilders.get("/grade/3"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(gradeService, times(1)).getGrade(3L);

    }

    @Test
    void getStudentExamGrade() throws Exception{

        Double grade_examBystudent = 3.8;

        when(gradeService.getStudentExamGrade(3L,4L)).thenReturn(grade_examBystudent);
        mockMvc.perform(MockMvcRequestBuilders.get("/grade/student/3/exam/4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data").value(3.8));

        verify(gradeService, times(1)).getStudentExamGrade(3L,4L);

    }

    @Test
    void addGrade() throws Exception{
        GradeRequest gradeRequest = new GradeRequest(8L,25L,3L,2.5);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(gradeRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/grade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(gradeService, times(1)).addGrade(gradeRequest);
    }

    @Test
    void updateGrade() throws Exception{
        GradeRequest gradeRequest = new GradeRequest(5L,31L,4L,2.9);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(gradeRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/grade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(gradeService, times(1)).updateGrade(gradeRequest);
    }

    @Test
    void deleteGrade() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/grade/3"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(gradeService, times(1)).deleteGrade(3L);
    }
}