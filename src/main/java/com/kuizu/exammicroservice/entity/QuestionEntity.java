package com.kuizu.exammicroservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "question")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Long idQuestion;
    @Column(name = "id_exam")
    @NonNull
    private Long idExam;
    @Column(name = "_description")
    @NonNull
    private String description;
    @Column(name = "_value")
    @NonNull
    private Double value;
}
