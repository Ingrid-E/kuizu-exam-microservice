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
@Table(name = "examxstudent")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamXStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_examxstudent")
    private Long idExamxStudent;
    @Column(name = "id_student")
    @NonNull
    private Long idStudent;
    @Column(name = "id_exam")
    @NonNull
    private Long idExam;
    @Column(name = "completed_at")
    @NonNull
    private LocalDateTime completedAt;

}
