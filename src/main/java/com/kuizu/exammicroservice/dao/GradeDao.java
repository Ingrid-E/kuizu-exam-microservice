package com.kuizu.exammicroservice.dao;

import com.kuizu.exammicroservice.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeDao extends JpaRepository<GradeEntity, Long> {
    Optional<List<GradeEntity>> findAllByIdExam(Long idExam);
    Optional<GradeEntity> findByIdStudentAndIdExam(Long idStudent, Long idExam);
}
