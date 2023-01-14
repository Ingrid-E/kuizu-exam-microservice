package com.kuizu.exammicroservice.dao;

import com.kuizu.exammicroservice.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamDao extends JpaRepository<ExamEntity, Long> {
    Optional<List<ExamEntity>> findAllByIdCourse(String idCourse);
}
