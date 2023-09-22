package com.project.hamsterd.service;

import com.project.hamsterd.domain.*;
import com.project.hamsterd.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InCommentService {

    @Autowired
    private InCommentDAO dao;



    //Create
    //대댓글 등록
    public InComment create(InComment vo){
        return dao.save(vo);
    }

    //Read
    //대댓글 조회
    public List<InComment> showAll(int postNo, int commentNo){
        return dao.findByCommentNo(postNo, commentNo);

    }

    //Update
    //대댓글 작성시간
    public InComment update(InComment vo){

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

        //기존 incomment 정보 받아오기
        InComment incomment = dao.findById(vo.getInCoNo()).orElse(null);
        //최초 대댓글 시간
        vo.setCreateDate(incomment.getCreateDate());
        //수정 대댓글 시간
        vo.setUpdateDate(formattedDate);
        vo.setMember(incomment.getMember());
        vo.setPost(incomment.getPost());
vo.setPostComment(incomment.getPostComment());

        return dao.save(vo);
    }
    //Delete
    //대댓글 삭제하기
    public InComment delete(int id){
        InComment target = dao.findById(id).orElse(null);
        dao.delete(target);

        return target;
    }


}
