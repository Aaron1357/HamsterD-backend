package com.hamsterD.MemberService;

import com.hamsterD.MemberDAO.MemberDAO;
import com.project.hamsterd.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MemberService {

    @Autowired
    private MemberDAO dao;

    public List<Member> showAll() {
     return dao.findAll();
    }

    public Member show(int id){
        return dao.findById(id).orElse(null);

    }
    public Member create(Member member) {
        return dao.save(member);
    }

    public Member update(Member member) {
        Member target = dao.findById(member.getMemberNo()).orElse(null);

        if (target != null) return dao.save(member);
       return null;
    }

   public Member delete(int id) {
        Member target = dao.findById(id).orElse(null);
        dao.delete(target);
        return target;
   }

}
