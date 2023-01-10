package com.kuizu.exammicroservice.dao.Repository;
import com.kuizu.exammicroservice.dao.ExamDao;
import com.kuizu.exammicroservice.entity.ExamEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

@Repository
@Data
@RequiredArgsConstructor
@Log
public class ExamRepository {
    private final ExamDao examDao;

    public ExamEntity save(ExamEntity examEntity){
        log.info("Save Repository");
        ExamEntity exam = new ExamEntity();
        try{
            log.info("Before sended: " + exam.toString());
            exam = examDao.save(examEntity);
            log.info("After sended: " + exam.toString());
        }catch (Exception ex){
            log.info(ex.getMessage());
        }
        return exam;
    }
}
