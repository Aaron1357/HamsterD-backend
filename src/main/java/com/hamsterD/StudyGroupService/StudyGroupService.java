package com.hamsterD.StudyGroupService;

import com.hamsterD.StudyGroupDAO.StudyGroupDAO;
import com.hamsterD.StudyGroupVO.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupService {

    @Autowired
    private StudyGroupDAO dao;

    public List<StudyGroup> showAll(){
        return dao.findAll();
    }

    public StudyGroup show(int id){
        return dao.findById(id).orElse(null);
    }

    public StudyGroup create(StudyGroup studyGroup){
        return dao.save(studyGroup);
    }

    public StudyGroup update(StudyGroup studyGroup){

        StudyGroup target = dao.findById(studyGroup.getGroup_no()).orElse(null);

        if(target!=null) return dao.save(studyGroup);

        return null;
    }
    public StudyGroup delete(int id){

        StudyGroup target = dao.findById(id).orElse(null);

        dao.delete(target);

        return target;
    }

}
