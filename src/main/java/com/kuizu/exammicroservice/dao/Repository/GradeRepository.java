package com.kuizu.exammicroservice.dao.Repository;

import com.kuizu.exammicroservice.dao.GradeDao;
import com.kuizu.exammicroservice.entity.GradeEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Data
@RequiredArgsConstructor
@Log
public class GradeRepository {
    private final GradeDao gradeDao;

    public List<GradeEntity> getExamGrades(Long idExam){
        return gradeDao.findAllByIdExam(idExam).orElse(null);
    }

    public List<GradeEntity> getGrades(){
        return gradeDao.findAll();
    }

    public Double getExamGradesAverage(Long idExam){
        return gradeDao.findAllByIdExam(idExam).stream()
                .flatMap(List::stream)
                .mapToDouble(GradeEntity::getValue)
                .average()
                .orElse(0);
    }

    public GradeEntity saveGrade(GradeEntity gradeEntity){
        GradeEntity existingGrade = gradeDao.findByIdStudentAndIdExam(gradeEntity.getIdStudent(), gradeEntity.getIdExam()).orElse(null);
        if(existingGrade != null){
            gradeEntity.setIdGrade(existingGrade.getIdGrade());
        }
        return gradeDao.save(gradeEntity);
    }

    public void deleteGrade(Long idGrade){
        gradeDao.deleteById(idGrade);
    }

    public GradeEntity getGrade(Long idGrade){
        return gradeDao.findById(idGrade).orElse(null);
    }

}
