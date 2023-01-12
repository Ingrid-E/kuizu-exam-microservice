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

import java.time.LocalDateTime;

@Entity
@Table(name = "grade")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grade")
    private Long idGrade;
    @Column(name = "id_student")
    @NonNull
    private Long idStudent;
    @Column(name = "id_exam")
    @NonNull
    private Long idExam;
    @Column(name = "_value")
    @NonNull
    private Double value;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
