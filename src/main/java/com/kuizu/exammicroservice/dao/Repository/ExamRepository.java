package com.kuizu.exammicroservice.dao.Repository;
import com.kuizu.exammicroservice.dao.ExamDao;
import com.kuizu.exammicroservice.dao.ExamXStudentDao;
import com.kuizu.exammicroservice.entity.ExamEntity;
import com.kuizu.exammicroservice.entity.ExamXStudentEntity;
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
    private final ExamXStudentDao examXStudentDao;

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

    public ExamEntity getExams(Long idExam){
        return examDao.findById(idExam).orElse(null);
    }

    public List<ExamEntity> getCourseExams(String idCourse){
        return examDao.findAllByIdCourse(idCourse).orElse(null);
    }

    public void deleteExam(Long idExam){
        examDao.deleteById(idExam);
    }

    public ExamXStudentEntity saveExamXStudent(ExamXStudentEntity examXStudentEntity){
        return examXStudentDao.save(examXStudentEntity);
    }

    public void deleteExamXStudent(ExamXStudentEntity examXStudentEntity){
        examXStudentDao.delete(examXStudentEntity);
    }

    public List<ExamXStudentEntity> listStudents(Long idExam){
        return examXStudentDao.findAllByIdExam(idExam).orElse(null);
    }
}
