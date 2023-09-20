package com.project.hamsterd.service;

import com.project.hamsterd.domain.CommentLike;
import com.project.hamsterd.domain.PostComment;
import com.project.hamsterd.repo.CommentLikeDAO;
import com.project.hamsterd.repo.PostCommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentLikeService {
    @Autowired
    private CommentLikeDAO dao;

    public List<CommentLike> showAll(){
        return dao.findAll();
    }

    public CommentLike show(int id){
        return dao.findById(id).orElse(null);
    }

    public CommentLike create(CommentLike vo){
        return dao.save(vo);
    }

    public CommentLike update(CommentLike vo){

        CommentLike target = dao.findById(vo.getCLikeNo()).orElse(null);

        if(target != null) return dao.save(vo);

        return null;
    }

    public CommentLike delete(int id){
        CommentLike target = dao.findById(id).orElse(null);
        dao.delete(target);

        return target;
    }

}
