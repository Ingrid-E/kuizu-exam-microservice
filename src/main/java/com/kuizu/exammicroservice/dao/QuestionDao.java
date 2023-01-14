package com.kuizu.exammicroservice.dao;

import com.kuizu.exammicroservice.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionDao extends JpaRepository<QuestionEntity, Long> {
    Optional<List<QuestionEntity>> findAllByIdExam(Long idExam);
}
