package com.project.hamsterd.service;

import com.project.hamsterd.repo.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostDAO dao;
}
