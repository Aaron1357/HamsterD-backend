package com.project.hamsterd.controller;

import com.project.hamsterd.domain.StudyGroup;
import com.project.hamsterd.service.MemberService;
import com.project.hamsterd.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
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
        log.info("전체조회!!");
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    @GetMapping("/member/{id}/{password}")
    public ResponseEntity<Member> show(@PathVariable String id, @PathVariable String password) {
        System.out.println("멤버 개별 조회 들어옴?");
        System.out.println("id : " + id);
        System.out.println("pw : " + password);

//        List<Member> list =  service.showAll();
//        for(int i = 0; i<list.size(); i++){
//            if(member.getId().equals(list.get(i).getId()) && member.getPassword().equals(list.get(i).getPassword())){
//                member = list.get(i);
//            }
//        }

        return ResponseEntity.status(HttpStatus.OK).body(service.show(id, password));
    }

    @PostMapping("/member")
    public ResponseEntity<Member> create(@ModelAttribute Member member) {
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


//          member.setStudentNo(++nextVal);

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