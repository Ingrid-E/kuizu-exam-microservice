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
@Table(name = "optionxstudent")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionxStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_optionxstudent")
    private Long idOptionxStudent;
    @Column(name = "id_student")
    @NonNull
    private Long idStudent;
    @Column(name = "id_option")
    private Long idOption;
}
