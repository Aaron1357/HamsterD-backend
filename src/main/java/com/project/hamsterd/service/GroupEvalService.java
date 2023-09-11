package com.project.hamsterd.service;

import com.project.hamsterd.repo.GroupEvalDAO;
import com.project.hamsterd.domain.GroupEval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupEvalService {

    @Autowired
    private GroupEvalDAO dao;

    public List<GroupEval> showAll(){
        return dao.findAll();
    }

    public GroupEval show(int id){
        return dao.findById(id).orElse(null);
    }

    public GroupEval create(GroupEval groupEval){
        return dao.save(groupEval);
    }

    public GroupEval update(GroupEval groupEval){

        GroupEval target = dao.findById(groupEval.getGroupRevNo()).orElse(null);

        if(target!=null) return dao.save(groupEval);

        return null;
    }

    public GroupEval delete(int id){
        GroupEval target = dao.findById(id).orElse(null);
        dao.delete(target);
        return target;
    }


}
