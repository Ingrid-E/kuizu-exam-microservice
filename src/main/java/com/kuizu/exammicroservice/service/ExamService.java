package com.kuizu.exammicroservice.service;

import com.kuizu.exammicroservice.controller.Response.ExamResponse;
import com.kuizu.exammicroservice.dao.ExamDao;
import com.kuizu.exammicroservice.dao.Repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    public ExamResponse createExam(){
        return null;
    }
}
