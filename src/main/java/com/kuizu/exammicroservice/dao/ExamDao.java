package com.kuizu.exammicroservice.dao;

import com.kuizu.exammicroservice.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamDao extends JpaRepository<ExamEntity, Integer> {
}
