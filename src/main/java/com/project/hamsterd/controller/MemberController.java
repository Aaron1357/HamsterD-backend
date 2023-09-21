package com.project.hamsterd.controller;

import com.project.hamsterd.domain.StudyGroup;
import com.project.hamsterd.service.MemberService;
import com.project.hamsterd.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/hamsterd/*")
@Log4j2
@CrossOrigin(origins = {"*"}, maxAge = 6000)
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
        log.info(member);
//        StudyGroup group  = new StudyGroup();
//        group.setGroupNo(0);
//        member.setStudyGroup(group);
//        Member mem = new Member();
//        mem.setMemberId("user1");
//        mem.setMemberAge(24);
//        mem.setMemberName("윤종빈");
//        mem.setAcademyName("kh");
//        mem.setStudentNo(1);


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