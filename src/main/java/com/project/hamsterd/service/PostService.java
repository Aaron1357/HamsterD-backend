package com.project.hamsterd.service;

import com.project.hamsterd.domain.Post;
import com.project.hamsterd.repo.PostDAO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Post> showAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    public Post show(int postNo) {
        log.info("show postNo " + postNo);
        Post post = dao.findById(postNo).orElse(null);
        log.info(post);

        return post;
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
        post.setCreateTime(create.getCreateTime());
        post.setBoardView(create.getBoardView());
        post.setSecurityCheck(create.getSecurityCheck());
        post.setUpdateTime(formattedDate);
        log.info("create: " + create);
        if(create!=null){
            return dao.save(post);
        }
        return null;
    }

    public Post delete(int postNo){
        Post data = dao.findById(postNo).orElse(null);

        if(data !=null) {
            dao.delete(data);
        }
        return null;
    }

    public List<Post> findByMemberId(int id) {
        return dao.findByMemberId(id);
    }

    //게시판 조회수 업데이트
    public Post updateBoardView(int postNo) {
        log.info("조회수 서비스");
        log.info("postNo 서비스 " + postNo);
        return dao.updateBoardView(postNo);
    }
}
