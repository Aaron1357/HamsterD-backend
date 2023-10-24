package com.project.hamsterd.service;

import com.project.hamsterd.domain.Post;
import com.project.hamsterd.domain.PostComment;
import com.project.hamsterd.repo.PostCommentDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PostCommentService {
    @Autowired
    private PostCommentDAO dao;

    public List<PostComment> showAll(){
        return dao.findAll();
    }

    public PostComment show(int id){
        return dao.findById(id).orElse(null);
    }

    public PostComment create(PostComment vo){
        return dao.save(vo);
    }

    public PostComment update(PostComment vo){

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


        PostComment create = dao.findById(vo.getCommentNo()).orElse(null);

        vo.setCreateDate(create.getCreateDate());
        vo.setUpdateDate(formattedDate);
        vo.setMember(create.getMember());
        vo.setPost(create.getPost());
        log.info("create: " + create);
        if(create!=null){
            return dao.save(vo);
        }
        return null;

    }

    public PostComment delete(int commentNo){
        PostComment target = dao.findById(commentNo).orElse(null);
        dao.delete(target);

        return target;
    }

    public List<PostComment> findByPostNo(int postNo){
        return dao.findByPostNo(postNo);
    }
}
