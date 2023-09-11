package com.hamsterD.MemberService;

import com.hamsterD.MemberDAO.MemberDAO;
import com.hamsterD.MemberVo.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MemberService {

    @Autowired
    private MemberDAO dao;

    public List<Model> showAll() {
     return dao.findAll();
    }

    public Model show(int id){
        return dao.findById(id).orElse(null);

    }
    public Model create(Model model) {
        return dao.save(model);
    }

    public Model update(Model model) {
        Model target = dao.findById(model.getMember_no()).orElse(null);

        if (target != null) return dao.save(model);
       return null;
    }

   public Model delete(int id) {
        Model target = dao.findById(id).orElse(null);
        dao.delete(target);
        return target;
   }

}
