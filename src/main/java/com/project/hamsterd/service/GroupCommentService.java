package com.project.hamsterd.service;

import com.project.hamsterd.domain.GroupComment;
import com.project.hamsterd.repo.GroupCommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class GroupCommentService {

    @Autowired
    private GroupCommentDAO dao;

    public List<GroupComment> showAll(){
        return dao.findAll();
    }

    public GroupComment show(int id){
        return dao.findById(id).orElse(null);
    }

    public GroupComment create(GroupComment vo){
        return dao.save(vo);
    }

    public GroupComment update(GroupComment vo){


        // 현재 날짜/시간
        Date now = new Date();
        // 현재 날짜/시간 출력
        System.out.println(now); // Thu May 03 14:43:32 KST 2022
        // 포맷팅 정의
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 포맷팅 적용
        String formatedNow = formatter.format(now);

        // 포맷팅 현재 날짜/시간 출력
        System.out.println(formatedNow); // 2023-09-21 04:24:57

        Date formattedDate = null;

        try {
            formattedDate = formatter.parse(formatedNow);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        GroupComment create = dao.findById(vo.getGCommentNo()).orElse(null);
        vo.setCreateDate(create.getCreateDate());

        vo.setUpdateDate(formattedDate);
        vo.setMember(create.getMember());
        vo.setStudyGroup(create.getStudyGroup());



        return dao.save(vo);




    }

    public GroupComment delete(int id){
        GroupComment target = dao.findById(id).orElse(null);
        dao.delete(target);

        return target;
    }

    public List<GroupComment> findByPostNo(int groupNo){
        return dao.findByGroupNo(groupNo);
    }
}
