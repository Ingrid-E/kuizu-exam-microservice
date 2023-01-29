package com.kuizu.exammicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuizu.exammicroservice.controller.Request.OptionRequest;
import com.kuizu.exammicroservice.controller.Request.OptionXStudentRequest;
import com.kuizu.exammicroservice.controller.Response.GetExamResponse;
import com.kuizu.exammicroservice.controller.Response.GetOptionResponse;
import com.kuizu.exammicroservice.service.GradeService;
import com.kuizu.exammicroservice.service.OptionService;
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
@WebMvcTest(OptionController.class)
class OptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OptionService optionService;
    @Test
    void createOption() throws Exception{
        OptionRequest optionRequest = new OptionRequest(2L,10L,
                "What color is polar bear skin?", "multiple-order",true);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(optionRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/exam/question/option")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(optionService, times(1)).createOption(optionRequest);
    }

    @Test
    void getQuestionOptions() throws Exception{

        OptionRequest optionRequest = new OptionRequest(3L,4L,
                "Candy", "multiple-order",false);


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(optionRequest);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/question/option")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(optionService, times(1)).getQuestionOptions(4L);
    }

    @Test
    void getOption() throws Exception{

        GetOptionResponse getOptionResponse = new GetOptionResponse(2L,14L,"Yuca",
                "multiple-order",false);

        when(optionService.getOption(2L)).thenReturn(getOptionResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/question/option/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(optionService, times(1)).getOption(2L);
    }

    @Test
    void updateQuestion() throws Exception{
        OptionRequest optionRequest = new OptionRequest(5L,33L,
                "Fox", "multiple-order",true);


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(optionRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/exam/question/option")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(optionService, times(1)).updateQuestion(optionRequest);
    }

    @Test
    void deleteOption() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/exam/question/option/7"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(optionService, times(1)).deleteOption(7L);
    }

    @Test
    void optionChosen() throws Exception{
        OptionXStudentRequest option_student_request = new OptionXStudentRequest(62L,24L,3L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(option_student_request);

        mockMvc.perform(MockMvcRequestBuilders.post("/exam/question/option/chosen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(optionService, times(1)).addOptionXStudent(option_student_request);

    }

    @Test
    void deleteOptionChosen() throws Exception{

        OptionXStudentRequest option_student_request = new OptionXStudentRequest(62L,24L,3L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(option_student_request);

        mockMvc.perform(MockMvcRequestBuilders.delete("/exam/question/option/chosen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(optionService, times(1)).deleteOptionXStudent(option_student_request);
    }

    @Test
    void listOptionsChosen() throws Exception{

        OptionXStudentRequest option_student_request = new OptionXStudentRequest(121L,34L,8L);


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(option_student_request);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/question/option/chosen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(optionService, times(1)).listStudentOptions(34L);
    }
}