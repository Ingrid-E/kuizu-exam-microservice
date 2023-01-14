package com.kuizu.exammicroservice.dao;

import com.kuizu.exammicroservice.entity.OptionxStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionXStudentDao extends JpaRepository<OptionxStudentEntity, Long> {
    Optional<OptionxStudentEntity> findByIdStudentAndIdOption(Long idStudent, Long idOption);
    Optional<List<OptionxStudentEntity>> findAllByIdStudent(Long idStudent);
}
