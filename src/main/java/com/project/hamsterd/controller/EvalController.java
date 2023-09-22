package com.project.hamsterd.controller;


import com.project.hamsterd.domain.GroupEval;
import com.project.hamsterd.domain.PersonalEval;
import com.project.hamsterd.service.GroupEvalService;
import com.project.hamsterd.service.PersonalEvalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hamsterd/*")
public class EvalController {

    @Autowired
    private GroupEvalService groupEvalService;

    @Autowired
    private PersonalEvalService personalEvalService;

    //    http://localhost:8080/hamsterd/groupeval
    @GetMapping("/groupeval")
    public ResponseEntity<List<GroupEval>> showAllEval(){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.showAll());
    }


    //    http://localhost:8080/hamsterd/groupeval/1
    @GetMapping("/groupeval/{id}")
    public ResponseEntity<GroupEval> showEval(@PathVariable int id){
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
    public ResponseEntity<GroupEval> deleteEval(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.delete(id));
    }

    //    http://localhost:8080/hamsterd/groupeval/eval?mno=1&gno=1
    @GetMapping("/groupeval/eval")
    public ResponseEntity<List<GroupEval>> showMemberAndGroup(@RequestParam int mno, @RequestParam int gno){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.showMemberAndGroup(mno, gno));
    }

    //    http://localhost:8080/hamsterd/pereval
    @GetMapping("/pereval")
    public ResponseEntity<List<PersonalEval>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.showAll());
    }

    //    http://localhost:8080/hamsterd/pereval/1
    @GetMapping("/pereval/{id}")
    public ResponseEntity<PersonalEval> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.show(id));
    }

    //    http://localhost:8080/hamsterd/pereval
    @PostMapping("/pereval")
    public ResponseEntity<PersonalEval> create(@RequestBody PersonalEval perEval){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.create(perEval));
    }


//    http://localhost:8080/hamsterd/pereval
    @PutMapping("/pereval")
    public ResponseEntity<PersonalEval> update(@RequestBody PersonalEval perEval){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.update(perEval));
    }

//    http://localhost:8080/hamsterd/pereval/1
    @DeleteMapping("/pereval/{id}")
    public ResponseEntity<PersonalEval> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.delete(id));
    }


//    http://localhost:8080/hamsterd/pereval/eval?no=1
    @GetMapping("/pereval/eval")
    public ResponseEntity<List<PersonalEval>> showMember(@RequestParam int no){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.showMemberEval(no));
    }


}
