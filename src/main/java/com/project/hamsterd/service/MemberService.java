package com.project.hamsterd.service;

//import com.project.hamsterd.SecurityConfig;
import com.project.hamsterd.repo.MemberDAO;
import com.project.hamsterd.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MemberService {

    @Autowired
    private MemberDAO dao;

//    private SecurityConfig security;


    public List<Member> showAll() {
        return dao.findAll();
    }

    public Member findById(String id){
        return dao.findByMemberId(id);
    }


    public Member findByNick(String nick){
        return dao.findByNick(nick);
    }


    public Member create(Member member) {


//        String encodedPw = security.passwordEncoder().encode(member.getPassword());

//        System.out.println("암호화 된 비번 : " + encodedPw);
//        member.setPassword(encodedPw);

        return dao.save(member);
    }

    public Member update(Member member) {
        Member target = dao.findByMemberId(member.getId());
//        System.out.println("인코딩 된 패스워드(수정) : " + member.getPassword());
//        System.out.println("닉네임 (수정) : " + member.getNickname());
//        System.out.println("멤버 식별용 아이디 : " + member.getId());


        if (target != null) {
            target.setPassword(member.getPassword());
            target.setNickname(member.getNickname());
            return dao.save(target);
        }
        return null;
    }

    public Member delete(int id) {
        Member target = dao.findById(id).orElse(null);
        dao.delete(target);
        return target;
    }

    public Member getByCredentials(String id, String password, PasswordEncoder encoder){
        Member member = dao.findByMemberId(id);

        if(member != null && encoder.matches(password, member.getPassword())){
            return member;
        }

        return null;
    }

}