package com.kuizu.exammicroservice.controller.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class GetStudent {
    private Long idStudent;
    private LocalDateTime completed_at;
}
