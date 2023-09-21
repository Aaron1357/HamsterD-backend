package com.project.hamsterd.service;

import com.project.hamsterd.domain.Post;
import com.project.hamsterd.repo.PostDAO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class PostService {
    @Autowired
    private PostDAO dao;

    public List<Post> showAll() {
        return dao.findAll();
    }

    public Post show(int postNo) {
        return dao.findById(postNo).orElse(null);
    }

    public Post create(Post post) {
        return dao.save(post);
    }

    public Post update(Post post) {

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


        Post create = dao.findById(post.getPostNo()).orElse(null);
        post.setCreateTime( create.getCreateTime());

        post.setUpdateTime(formattedDate);



        return dao.save(post);
    }

    public Post delete(int postNo){
        Post data = dao.findById(postNo).orElse(null);
        dao.delete(data);
        return data;
    }

    public List<Post> findByMemberId(String id) {
        return dao.findByMemberId(id);
    }

}
