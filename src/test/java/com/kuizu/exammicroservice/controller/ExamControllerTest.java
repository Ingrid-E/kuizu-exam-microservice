package com.kuizu.exammicroservice.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuizu.exammicroservice.controller.Request.ExamRequest;
import com.kuizu.exammicroservice.controller.Request.ExamStudentOptionsRequest;
import com.kuizu.exammicroservice.controller.Request.ExamXStudentRequest;
import com.kuizu.exammicroservice.controller.Response.GetExamResponse;
import com.kuizu.exammicroservice.service.ExamService;
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

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExamController.class)
class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;
    @Test
    void getAllExams() throws Exception{

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

        when(examService.getExams()).thenReturn(myExams);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data[0].name").value("Cui asado"));

        verify(examService, times(1)).getExams();

    }

    @Test
    void getOneExam() throws Exception{
        GetExamResponse examResponse = new GetExamResponse(4L,
                "Yoga",
                "Reflexiona mucho",
                LocalDateTime.of(2023,2,9,18,0,0),
                LocalDateTime.of(2023,3,8,18,0,0),
                30,
                "active",
                "1A3D");

        when(examService.getExamRepository(4L)).thenReturn(examResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data.name").value("Yoga"));

        verify(examService, times(1)).getExamRepository(4L);
    }

    @Test
    void getExamCourses() throws Exception{
        List<GetExamResponse> exams_by_course = Arrays.asList(
                new GetExamResponse(                8L,
                        "San Valentin",
                        "¿Es en febrero o en septiembre?",
                        LocalDateTime.of(2023,2,9,18,0,0),
                        LocalDateTime.of(2023,3,8,18,0,0),
                        5,
                        "active",
                        "1A3D"),
                new GetExamResponse(                10L,
                        "Concurso de comida",
                        "Come la torta de chocolate cuando te lo ordene!",
                        LocalDateTime.of(2023,9,3,16,0,0),
                        LocalDateTime.of(2023,11,8,21,0,0),
                        5,
                        "active",
                        "1A3D")
        );

        when(examService.getCourseExams("1A3D")).thenReturn(exams_by_course);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/course/1A3D"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data[0].name").value("San Valentin"));

        verify(examService, times(1)).getCourseExams("1A3D");
    }

    @Test
    void getStudentCourseAvg() throws Exception{
        List<GetExamResponse> exams_course_avg = Arrays.asList(
                new GetExamResponse(                8L,
                        "Test de agudos",
                        "Los 3 mejores están dentro del equipo agudo!",
                        LocalDateTime.of(2023,1,29,6,0,0),
                        LocalDateTime.of(2023,10,10,14,0,0),
                        5,
                        "active",
                        "64YAG3UM"),
                new GetExamResponse(                10L,
                        "Test de graves",
                        "Los 3 mejores están dentro del equipo agudo!",
                        LocalDateTime.of(2023,1,29,6,0,0),
                        LocalDateTime.of(2023,10,10,14,0,0),
                        5,
                        "active",
                        "64YAG3UM")

        );
        when(examService.getCourseExams("64YAG3UM")).thenReturn(exams_course_avg);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/course/64YAG3UM/student/4"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(examService, times(1)).getCourseStudentAvg("64YAG3UM",4L);
    }

    @Test
    void createExam() throws Exception{
        ExamRequest examRequest = new ExamRequest(12L,"Ballet",
                "Baila perfecto y sorprende a la reina!",
                "1977-10-23 18:00",
                "1977-10-23 21:00",
                45,
                "active",
                "B4ll3R1N3");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(examRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/exam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(examService, times(1)).createExam(examRequest);
    }

    @Test
    void updateExam() throws Exception{
        ExamRequest examRequest = new ExamRequest(3L,"Concurso de comida",
                "Come la torta de chocolate cuando te lo ordene!",
                "2022-05-10 15:00",
                "2023-08-22 19:00",
                5,
                "active",
                "1A3D");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(examRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/exam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(examService, times(1)).updateExam(examRequest);
    }

    @Test
    void setExamStatus() throws Exception{
        List<GetExamResponse> exams_by_course = Arrays.asList(
                new GetExamResponse(8L,
                        "San Valentin",
                        "¿Es en febrero o en septiembre?",
                        LocalDateTime.of(2023,2,9,18,0,0),
                        LocalDateTime.of(2023,3,8,18,0,0),
                        5,
                        "active",
                        "14kD"),
                new GetExamResponse(                10L,
                        "Concurso de comida",
                        "Come la torta de chocolate cuando te lo ordene!",
                        LocalDateTime.of(2023,9,3,16,0,0),
                        LocalDateTime.of(2023,11,8,21,0,0),
                        5,
                        "active",
                        "14kD")

        );

        when(examService.getCourseExams("14kD")).thenReturn(exams_by_course);

        mockMvc.perform(MockMvcRequestBuilders.put("/exam/14KD"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(examService, times(1)).changeExamStatus("14KD");
    }

    @Test
    void deleteExam() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/exam/5"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(examService, times(1)).deleteExam(5L);
    }

    @Test
    void addStudentExam() throws Exception{
        ExamXStudentRequest exam_student_request = new ExamXStudentRequest(11L,6L,2L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(exam_student_request);

        mockMvc.perform(MockMvcRequestBuilders.post("/exam/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(examService, times(1)).addExamXStudent(exam_student_request);
    }

    @Test
    void deleteStudentExam() throws Exception{
        ExamXStudentRequest exam_student_request = new ExamXStudentRequest(123L,182L,32L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(exam_student_request);

        mockMvc.perform(MockMvcRequestBuilders.delete("/exam/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(examService, times(1)).deleteExamXStudent(exam_student_request);
    }

    @Test
    void listStudents() throws Exception{
        ExamRequest examRequest = new ExamRequest(3L,"Gayageum",
                "La musica clasica le gana al rock!",
                "2011-10-23 18:00",
                "2011-10-23 21:00",
                45,
                "active",
                "Rockgeum");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(examRequest);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(examService, times(1)).listStudents(3L);
    }

    @Test
    void listStudentChosenOptions() throws Exception{
        ExamStudentOptionsRequest exam_student_options_request = new ExamStudentOptionsRequest(4L,11L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(exam_student_options_request);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/student/response")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(examService, times(1)).studentQuestionResults(11L,4L);

    }
}