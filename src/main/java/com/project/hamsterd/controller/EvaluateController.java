package com.project.hamsterd.controller;

import com.project.hamsterd.service.EvaluateService;
import com.project.hamsterd.vo.Evaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studygroup/*")
public class EvaluateController {

    @Autowired
    private EvaluateService service;

    @GetMapping("/allreview")
    public ResponseEntity<List<Evaluate>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<Evaluate> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
    }

    @PostMapping("/createreview")
    public ResponseEntity<Evaluate> create(@RequestBody Evaluate evaluate){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(evaluate));
    }

    @PutMapping("/updatereview")
    public ResponseEntity<Evaluate> update(@RequestBody Evaluate evaluate){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(evaluate));
    }

    @DeleteMapping("/deletereview/{id}")
    public ResponseEntity<Evaluate> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
