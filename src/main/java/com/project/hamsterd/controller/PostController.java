package com.project.hamsterd.controller;

import com.project.hamsterd.domain.Post;
import com.project.hamsterd.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService service;

    //C : 게시판 작성하기
    @PostMapping("/post")
    public ResponseEntity <Post> create(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(post));
    }

    //C : 관리자 공지 글 작성하기 -- 관리자만 작성할수있음 관리자만 삭제가능


    //C : 게시판 임시 글 작성하기



    //R : 전체 게시판 보기
    @GetMapping("/post")
    public ResponseEntity<List<Post>> showAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    //R : 특정 게시판 보기
    @GetMapping("/post/{postNo}")
    public ResponseEntity <Post> show(@PathVariable int postNo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.show(postNo));
    }

    //R :
    // 특정 멤버의 모든 게시판 조회 memberNo 받아와서 작성하기
    //메서드명 임의로 지정 member한테 받아오기
//    public ResponseEntity <List<Post>> showMember(@RequestParam int memberNo) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.showMember());
//    }

    //U : 내 게시판 수정하기

    /*내 게시판만 수정할수있음 memberNo에 postNo으로 해야해*/
    @PutMapping("/post")
    public ResponseEntity <Post> update(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(post));
    }

    //D : 내 게시판 삭제하기
    /*내 게시판만 지울수있음 memberNo에 postNo으로 해야해*/
    @DeleteMapping("/post/{postNo}")
    public ResponseEntity <Post> delete(@PathVariable int postNo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(postNo));
    }

}
