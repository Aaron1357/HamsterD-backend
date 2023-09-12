package com.project.hamsterd.controller;

import com.project.hamsterd.domain.CommentLike;
import com.project.hamsterd.domain.PostComment;
import com.project.hamsterd.service.CommentLikeService;
import com.project.hamsterd.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hamsterd/*")
public class PostCommentController {

    @Autowired
    private PostCommentService pCommentService;

    @GetMapping("/pcomment")
    public ResponseEntity<List<PostComment>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.showAll());
    }

    @GetMapping("/pcomment/{id}")
    public ResponseEntity<PostComment> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.show(id));
    }

    @PostMapping("/pcomment")
    public ResponseEntity<PostComment> create(@RequestBody PostComment vo){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.create(vo));
    }

    @PutMapping("/pcomment")
    public ResponseEntity<PostComment> update(@RequestBody PostComment vo){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.update(vo));
    }

    @DeleteMapping("/pcomment/{id}")
    public ResponseEntity<PostComment> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.delete(id));
    }

}
