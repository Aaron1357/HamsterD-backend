package com.project.hamsterd.controller;

import com.project.hamsterd.service.GroupEvalService;
import com.project.hamsterd.domain.GroupEval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hamsterd/*")
public class GroupEvalController {

    @Autowired
    private GroupEvalService groupEvalService;


    //    http://localhost:8080/hamsterd/groupeval
    @GetMapping("/groupeval")
    public ResponseEntity<List<GroupEval>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.showAll());
    }


    //    http://localhost:8080/hamsterd/groupeval/1
    @GetMapping("/groupeval/{id}")
    public ResponseEntity<GroupEval> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.show(id));
    }


    //    http://localhost:8080/hamsterd/groupeval
    @PostMapping("/groupeval")
    public ResponseEntity<GroupEval> create(@RequestBody GroupEval groupEval){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.create(groupEval));
    }

    //    http://localhost:8080/hamsterd/groupeval
    @PutMapping("/groupeval")
    public ResponseEntity<GroupEval> update(@RequestBody GroupEval groupEval){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.update(groupEval));
    }


    //    http://localhost:8080/hamsterd/groupeval/1
    @DeleteMapping("/groupeval/{id}")
    public ResponseEntity<GroupEval> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.delete(id));
    }

    //    http://localhost:8080/hamsterd/groupeval/eval?mno=1&gno=1

    @GetMapping("/groupeval/eval")
    public ResponseEntity<List<GroupEval>> showMemberAndGroup(@RequestParam int mno, @RequestParam int gno){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.showMemberAndGroup(mno, gno));
    }

}
