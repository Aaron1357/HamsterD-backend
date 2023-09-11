package com.project.hamsterd.controller;

import com.project.hamsterd.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PostController {
    @Autowired
    private PostService service;


}
