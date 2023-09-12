package com.project.hamsterd.service;

import com.project.hamsterd.domain.Post;
import com.project.hamsterd.domain.PostComment;
import com.project.hamsterd.repo.PostCommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

        PostComment target = dao.findById(vo.getCommentNo()).orElse(null);

        if(target != null) return dao.save(vo);

        return null;
    }

    public PostComment delete(int id){
        PostComment target = dao.findById(id).orElse(null);
        dao.delete(target);

        return target;
    }

    public List<PostComment> findByCommentNo(int commentNo){
        return dao.findByCommentNo(commentNo);
    }
}
