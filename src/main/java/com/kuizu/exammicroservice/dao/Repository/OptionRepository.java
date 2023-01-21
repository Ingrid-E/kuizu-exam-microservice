package com.kuizu.exammicroservice.dao.Repository;

import com.kuizu.exammicroservice.dao.OptionDao;
import com.kuizu.exammicroservice.dao.OptionXStudentDao;
import com.kuizu.exammicroservice.entity.OptionEntity;
import com.kuizu.exammicroservice.entity.OptionxStudentEntity;
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
public class OptionRepository {
    private final OptionDao optionDao;
    private final OptionXStudentDao optionXStudentDao;

    public OptionEntity save(OptionEntity optionEntity){
        OptionEntity option = new OptionEntity();
        try{
            option = optionDao.save(optionEntity);
        }catch (Exception ex){
            log.info(ex.getMessage());
        }
        return option;
    }

    public List<OptionEntity> getQuestionOptions(Long idQuestion){
        return optionDao.findAllByIdQuestion(idQuestion).orElse(null);
    }

    public OptionEntity getOption(Long idOption){
        return optionDao.findById(idOption).orElse(null);
    }

    public void deleteOption(Long idOption){
        optionDao.deleteById(idOption);
    }

    public OptionxStudentEntity saveOptionXStudent(OptionxStudentEntity optionxStudentEntity){

        OptionxStudentEntity existingOptionXstudent = findOptionXStudent(optionxStudentEntity.getIdStudent(), optionxStudentEntity.getIdOption());
        if(existingOptionXstudent != null){
            optionxStudentEntity.setIdOptionxStudent(existingOptionXstudent.getIdOptionxStudent());
        }
        return optionXStudentDao.save(optionxStudentEntity);
    }

    public OptionxStudentEntity findOptionXStudent(Long idStudent, Long idOption){
        return optionXStudentDao.findByIdStudentAndIdOption(idStudent, idOption).orElse(null);
    }

    public void deleteOptionXStudent(OptionxStudentEntity optionxStudentEntity){
        optionXStudentDao.delete(optionxStudentEntity);
    }

    public List<OptionxStudentEntity> listStudentChoices(Long idStudent){
        return  optionXStudentDao.findAllByIdStudent(idStudent).orElse(null);
    }

}
