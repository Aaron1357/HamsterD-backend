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

    //댓글 전체보기
//    @GetMapping("/pcomment")
//    public ResponseEntity<List<PostComment>> showAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.showAll());
//    }

    //해당 아이디가 작성한 댓글 보기
    @GetMapping("/pcomment/{id}")
    public ResponseEntity<PostComment> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.show(id));
    }

    //댓글 추가하기
    @PostMapping("/pcomment")
    public ResponseEntity<PostComment> create(@RequestBody PostComment vo){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.create(vo));
    }

    //댓글 수정하기
    @PutMapping("/pcomment")
    public ResponseEntity<PostComment> update(@RequestBody PostComment vo){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.update(vo));
    }

    //댓글 삭제하기
    @DeleteMapping("/pcomment/{id}")
    public ResponseEntity<PostComment> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.delete(id));
    }

}
