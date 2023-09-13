package com.project.hamsterd.service;

import com.project.hamsterd.domain.Post;
import com.project.hamsterd.domain.PostLike;
import com.project.hamsterd.repo.PostLikeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostLikeService {

    @Autowired
    private PostLikeDAO dao;

    public List<PostLike> showAll() {
        return dao.findAll();
    }

    public PostLike show(int likeNo) {
        return dao.findById(likeNo).orElse(null);
    }

    public PostLike create(PostLike likeNo) {
        return dao.save(likeNo);
    }

    public PostLike update(PostLike likeNo) {
        return dao.save(likeNo);
    }

    public PostLike delete(int likeNo){
        PostLike data = dao.findById(likeNo).orElse(null);
        dao.delete(data);
        return data;
    }

}
