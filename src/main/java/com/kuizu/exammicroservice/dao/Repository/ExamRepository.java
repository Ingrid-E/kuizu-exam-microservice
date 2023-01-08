package com.kuizu.exammicroservice.dao.Repository;

import com.kuizu.exammicroservice.dao.ExamDao;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Data
@RequiredArgsConstructor
public class ExamRepository {
    private final ExamDao examDao;

}
