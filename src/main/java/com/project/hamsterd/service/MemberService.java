package com.project.hamsterd.service;

import com.project.hamsterd.SecurityConfig;
import com.project.hamsterd.repo.MemberDAO;
import com.project.hamsterd.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MemberService {

    @Autowired
    private MemberDAO dao;

    private SecurityConfig security;


    public List<Member> showAll() {
        return dao.findAll();
    }

    public Member show(String id, String password){
        Member mem = dao.findByMemberId(id);

        if(mem.getId().equals(id) && mem.getPassword().equals(password)){
            return mem;
        }

        return null;

    }
    public Member create(Member member) {


        String encodedPw = security.passwordEncoder().encode(member.getPassword());

        System.out.println("암호화 된 비번 : " + encodedPw);
        member.setPassword(encodedPw);

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