package com.project.hamsterd.service;

import com.project.hamsterd.domain.Member;
import com.project.hamsterd.repo.MemberDAO;
import com.project.hamsterd.repo.StudyGroupDAO;
import com.project.hamsterd.domain.StudyGroup;
import com.project.hamsterd.repo.GroupEvalDAO;
import com.project.hamsterd.domain.GroupEval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupEvalService {

    @Autowired
    private GroupEvalDAO groupEvalDAO;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private StudyGroupDAO studyGroupDAO;

    public List<GroupEval> showMemberAndGroup(int memberNo, int groupNo){
        return groupEvalDAO.findByMemberNoAndGroupNo(memberNo, groupNo);
    }

    public List<GroupEval> showAll(){
        return groupEvalDAO.findAll();
    }

    public List<GroupEval> showGroupEval(int groupNo){
        List<GroupEval> groupEval = groupEvalDAO.findByGroupNo(groupNo);

        return groupEval;
    }


    public GroupEval show(int memberNo){

        GroupEval groupEval = groupEvalDAO.findByMemberNo(memberNo);
//        Member member = memberDAO.findById(groupEval.getMember().getMemberNo()).orElse(null);
//        StudyGroup group = studyGroupDAO.findById(groupEval.getStudyGroup().getGroupNo()).orElse(null);
//
//        groupEval.setMember(member);
//        groupEval.setStudyGroup(group);

        return groupEval;
    }

    public GroupEval create(GroupEval groupEval){
        return groupEvalDAO.save(groupEval);
    }

    public GroupEval update(GroupEval groupEval){

        GroupEval target = groupEvalDAO.findById(groupEval.getGroupRevNo()).orElse(null);

        if(target!=null) return groupEvalDAO.save(groupEval);

        return null;
    }

    public GroupEval delete(int id){
        GroupEval target = groupEvalDAO.findById(id).orElse(null);
        groupEvalDAO.delete(target);
        return target;
    }

    public List<GroupEval> findByMemberNoAndGroupNo(int mNo, int gNo){
        return groupEvalDAO.findByMemberNoAndGroupNo(mNo, gNo);
    }

    public Integer getGroupAVG(int groupNo){
        return groupEvalDAO.getGroupAVG(groupNo);
    }


}
