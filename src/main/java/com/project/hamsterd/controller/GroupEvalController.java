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
    private GroupEvalService service;

    @GetMapping("/review")
    public ResponseEntity<List<GroupEval>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<GroupEval> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
    }

    @PostMapping("/review")
    public ResponseEntity<GroupEval> create(@RequestBody GroupEval groupEval){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(groupEval));
    }

    @PutMapping("/review")
    public ResponseEntity<GroupEval> update(@RequestBody GroupEval groupEval){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(groupEval));
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<GroupEval> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
