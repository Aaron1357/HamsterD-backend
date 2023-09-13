package com.project.hamsterd.service;


import com.project.hamsterd.domain.Member;
import com.project.hamsterd.repo.MemberDAO;
import com.project.hamsterd.domain.PersonalEval;
import com.project.hamsterd.repo.PersonalEvalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalEvalService {

    @Autowired
    private PersonalEvalDAO personalEvalDAO;

    @Autowired
    private MemberDAO memberDAO;


    public List<PersonalEval> showMemberEval(int memberNo){
        return personalEvalDAO.findByMemberId(memberNo);
    }
    public List<PersonalEval> showAll(){
        return personalEvalDAO.findAll();
    }

    public PersonalEval show(int memberNo){
        PersonalEval perEval = personalEvalDAO.findById(memberNo).orElse(null);
        Member member = memberDAO.findById(perEval.getMember().getMemberNo()).orElse(null);
        perEval.setMember(member);
        return perEval;

    }

    public PersonalEval create(PersonalEval eval){
        return personalEvalDAO.save(eval);
    }

    public PersonalEval update(PersonalEval eval){

        PersonalEval target = personalEvalDAO.findById(eval.getRevNo()).orElse(null);
        if(target != null){
            return personalEvalDAO.save(eval);
        }


        return null;
    }

    public PersonalEval delete(int memberNo){
        PersonalEval target = personalEvalDAO.findById(memberNo).orElse(null);

        if(target!=null){
            personalEvalDAO.delete(target);
            return target;
        }

        return null;
    }

    public List<PersonalEval> findBymemberNo(int memberNo){
        return personalEvalDAO.findByMemberId(memberNo);
    }


}
