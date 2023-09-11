package com.project.hamsterd.service;

import com.hamsterD.MemberDAO.MemberDAO;
import com.hamsterD.MemberVo.Model;
import com.hamsterD.StudyGroupDAO.StudyGroupDAO;
import com.hamsterD.StudyGroupVO.StudyGroup;
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

    public GroupEval show(int id){
        GroupEval groupEval = groupEvalDAO.findById(id).orElse(null);
        Model member = memberDAO.findById(groupEval.getModel().getMemberNo()).orElse(null);
        StudyGroup group = studyGroupDAO.findById(groupEval.getStudyGroup().getGroupNo()).orElse(null);

        groupEval.setModel(member);
        groupEval.setStudyGroup(group);

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


}
