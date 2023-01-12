package com.kuizu.exammicroservice.dao.Repository;
import com.kuizu.exammicroservice.dao.ExamDao;
import com.kuizu.exammicroservice.entity.ExamEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Data
@RequiredArgsConstructor
@Log
public class ExamRepository {
    private final ExamDao examDao;

    public ExamEntity save(ExamEntity examEntity){
        ExamEntity exam = new ExamEntity();
        try{
            exam = examDao.save(examEntity);
        }catch (Exception ex){
            log.info(ex.getMessage());
        }
        return exam;
    }
    public List<ExamEntity> getExams(){
        return examDao.findAll();
    }

    public ExamEntity getExams(Integer idExam){
        return examDao.findById(idExam).orElse(null);
    }

    public List<ExamEntity> getCourseExams(String idCourse){
        return examDao.findAllByIdCourse(idCourse).orElse(null);
    }

    public void deleteExam(Integer idExam){
        examDao.deleteById(idExam);
    }
}
