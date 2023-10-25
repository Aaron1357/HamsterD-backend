package com.project.hamsterd.service;

import com.project.hamsterd.repo.StudyGroupDAO;
import com.project.hamsterd.domain.StudyGroup;
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

    public StudyGroup show(int groupNo){
//        System.out.println(dao.findById(groupNo).orElse(null));
        return dao.findById(groupNo).orElse(null);
    }

    public StudyGroup create(StudyGroup studyGroup){
        return dao.save(studyGroup);
    }

    public StudyGroup update(StudyGroup studyGroup){

        StudyGroup target = dao.findById(studyGroup.getGroupNo()).orElse(null);

        if(target!=null) return dao.save(studyGroup);

        return null;
    }
    public StudyGroup delete(int id){

        StudyGroup target = dao.findById(id).orElse(null);

        dao.delete(target);

        return target;
    }



}
