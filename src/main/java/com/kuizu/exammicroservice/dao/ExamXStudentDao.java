package com.kuizu.exammicroservice.dao;

import com.kuizu.exammicroservice.entity.ExamXStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamXStudentDao extends JpaRepository<ExamXStudentEntity, Long> {
    Optional<List<ExamXStudentEntity>> findAllByIdExam(Long idExam);
    Optional<ExamXStudentEntity> findByIdExamAndIdStudent(Long idExam, Long idStudent);

}
