package com.project.hamsterd.controller;


import com.project.hamsterd.domain.CommentLike;
import com.project.hamsterd.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequestMapping("/hamsterd/*")
public class CommentLikeController {



    @Autowired
    private CommentLikeService cLikeService;

    @GetMapping("/clike")
    public ResponseEntity<List<CommentLike>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(cLikeService.showAll());
    }

    @GetMapping("/clike/{id}")
    public ResponseEntity<CommentLike> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(cLikeService.show(id));
    }

    @PostMapping("/clike")
    public ResponseEntity<CommentLike> create(@RequestBody CommentLike vo){
        return ResponseEntity.status(HttpStatus.OK).body(cLikeService.create(vo));
    }

    @PutMapping("/clike")
    public ResponseEntity<CommentLike> update(@RequestBody CommentLike vo){
        return ResponseEntity.status(HttpStatus.OK).body(cLikeService.update(vo));
    }

    @DeleteMapping("/clike/{id}")
    public ResponseEntity<CommentLike> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(cLikeService.delete(id));
    }

}
