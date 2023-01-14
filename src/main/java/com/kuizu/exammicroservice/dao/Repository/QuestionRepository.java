package com.kuizu.exammicroservice.dao.Repository;

import com.kuizu.exammicroservice.dao.QuestionDao;
import com.kuizu.exammicroservice.entity.ExamEntity;
import com.kuizu.exammicroservice.entity.GradeEntity;
import com.kuizu.exammicroservice.entity.QuestionEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Data
@RequiredArgsConstructor
@Log
public class QuestionRepository {
    private final QuestionDao questionDao;

    public QuestionEntity save(QuestionEntity questionEntity){
        QuestionEntity question = new QuestionEntity();
        try{
            question = questionDao.save(questionEntity);
        }catch (Exception ex){
            log.info(ex.getMessage());
        }
        return question;
    }

    public List<QuestionEntity> getExamQuestions(Long idExam){
        return questionDao.findAllByIdExam(idExam).orElse(null);
    }

    public QuestionEntity getQuestion(Long idQuestion){
        return questionDao.findById(idQuestion).orElse(null);
    }

    public void deleteQuestion(Long idQuestion){
        questionDao.deleteById(idQuestion);
    }

}
