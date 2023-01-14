package com.kuizu.exammicroservice.dao;

import com.kuizu.exammicroservice.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionDao extends JpaRepository<OptionEntity, Long> {
    Optional<List<OptionEntity>> findAllByIdQuestion(Long idQuestion);

}
