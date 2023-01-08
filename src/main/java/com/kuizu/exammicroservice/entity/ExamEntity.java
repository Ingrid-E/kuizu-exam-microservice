package com.kuizu.exammicroservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_exam;
    @Column(name = "_name")
    @NonNull
    private String name;
    @Column(name = "_description")
    @NonNull
    private String description;
    @Column(name = "created_a")
    @NonNull
    private LocalDateTime createdAt;
    @Column(name = "start_at")
    private LocalDateTime startAt;
    @Column(name = "end_at")
    private LocalDateTime endAt;
    @Column(name = "time_limit")
    private Integer timeLimit;
    @Column(name = "state")
    @NonNull
    private String state;
    @Column(name = "id_course")
    @NonNull
    private String id_course;

}
