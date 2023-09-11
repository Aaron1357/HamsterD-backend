package com.project.hamsterd.service;

import com.project.hamsterd.dao.EvaluateDAO;
import com.project.hamsterd.vo.Evaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateService {

    @Autowired
    private EvaluateDAO dao;

    public List<Evaluate> showAll(){
        return dao.findAll();
    }

    public Evaluate show(int id){
        return dao.findById(id).orElse(null);
    }

    public Evaluate create(Evaluate evaluate){
        return dao.save(evaluate);
    }

    public Evaluate update(Evaluate evaluate){

        Evaluate target = dao.findById(evaluate.getGroupRevNo()).orElse(null);

        if(target!=null) return dao.save(evaluate);

        return null;
    }

    public Evaluate delete(int id){
        Evaluate target = dao.findById(id).orElse(null);
        dao.delete(target);
        return target;
    }


}
