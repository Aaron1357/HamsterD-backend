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
        Post post = dao.findById(postNo).orElse(null);
        return post;
    }

    public Post create(Post post) {
        return dao.save(post);
    }

    public Post update(Post post) {return dao.save(post);}

    public Post delete(int postNo){
        Post data = dao.findById(postNo).orElse(null);

        if(data !=null) {
            dao.delete(data);
        }
        return null;
    }

    //게시판 조회수 업데이트
    public Post updateBoardView(int postNo) {
        return dao.updateBoardView(postNo);
    }

    //검색창에 내용 조회 시 게시판 조회
    public Page<Post> findSearchContent(String postContent, Pageable pageable) {
        log.info("어딧니 컨텐츠야" +postContent);
        return dao.findSearchContent(postContent, pageable);
    }

    //검색창에 제목 조회 시 게시판 조회
    public Page<Post> findSearchTitle(String postTitle, Pageable pageable) {
        log.info("어딧니 제목" +postTitle);
        return dao.findSearchTitle(postTitle, pageable);
    }
}
