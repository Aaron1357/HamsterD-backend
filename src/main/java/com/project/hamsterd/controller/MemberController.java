package com.project.hamsterd.controller;

import com.project.hamsterd.service.MemberService;
import com.project.hamsterd.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/hamsterd/*")

public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("/member")
    public ResponseEntity<List<Member>> showAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Member> show(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
    }

    @PostMapping("/member")
    public ResponseEntity<Member> create(@RequestBody Member member) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(member));
    }

    @PutMapping("/member")
    public ResponseEntity<Member> update(@RequestBody Member member) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(member));
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<Member> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

}