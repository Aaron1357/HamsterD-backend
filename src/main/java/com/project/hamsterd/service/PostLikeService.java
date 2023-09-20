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
    /*좋아요 누른 사람 받아와야해서 create 해줘야함*/
    public PostLike create(PostLike likeNo) {
        return dao.save(likeNo);
    }

    public PostLike update(PostLike likeNo) {
        return dao.save(likeNo);
    }
    /*좋아요 누른 사람이 일치해야 삭제 가능하기 때문에 쿼리문 작성해줘야함*/
    public PostLike delete(int likeNo){
        PostLike data = dao.findById(likeNo).orElse(null);
        dao.delete(data);
        return data;
    }

}
