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
@Table(name = "_option")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_option")
    private Long idOption;
    @Column(name = "id_question")
    @NonNull
    private Long idQuestion;
    @Column(name = "_description")
    @NonNull
    private String description;
    @Column(name = "_type")
    private String type;
    @Column(name = "is_correct")
    @NonNull
    private Boolean isCorrect;
}
