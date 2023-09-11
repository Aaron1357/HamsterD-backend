package com.project.hamsterd.service;

import com.project.hamsterd.domain.Post;
import com.project.hamsterd.repo.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        return dao.save(post);
    }

    public Post delete(int postNo){
        Post data = dao.findById(postNo).orElse(null);
        dao.delete(data);
        return data;
    }


}
