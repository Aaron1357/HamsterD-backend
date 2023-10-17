package com.project.hamsterd.controller;

import com.project.hamsterd.domain.MemberDTO;
import com.project.hamsterd.domain.StudyGroup;
import com.project.hamsterd.security.TokenProvider;
import com.project.hamsterd.service.MemberService;
import com.project.hamsterd.domain.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.List;


@RestController
@RequestMapping("/hamsterd/*")
@Log4j2
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class MemberController {


    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MemberService service;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

        Member member = service.show(id, password);

//        if(member != null){
//            session.setAttribute("member", member);
////            return ResponseEntity.status(HttpStatus.OK).body();
//        }

        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @PostMapping("/member")
    public ResponseEntity<MemberDTO> create(@RequestBody MemberDTO dto) {
//        log.info(member);

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
    
        log.info("회원가입들어옴");
        Member member = Member.builder()
                                .id(dto.getId())
                                .password(passwordEncoder.encode(dto.getPassword()))
                                .name(dto.getName())
                                .birth(dto.getBirth())
                                .gender(dto.getGender())
                                .phone(dto.getPhone())
                                .academy(dto.getAcademy())
                                .address(dto.getAddress())
                                .nickname(dto.getNickname())
                                .build();

        Member registerMember = service.create(member);

        MemberDTO responseDTO = MemberDTO.builder()
                .id(registerMember.getId())
//                .password(registerMember.getPassword())
                .name(registerMember.getName())
                .birth(registerMember.getBirth())
                .gender(registerMember.getGender())
                .phone(registerMember.getPhone())
                .academy(registerMember.getAcademy())
                .address(registerMember.getAddress())
                .nickname(registerMember.getNickname())
                .build();

//        log.info(registerMember.toString());


        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PutMapping("/member")
    public ResponseEntity<Member> update(MultipartFile profile, @RequestParam(name = "id") String id, @RequestParam(name = "password") String password, @RequestParam(name = "nickname") String nickname) {
        Member member = Member.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .build();
        log.info(service.update(member));

        return ResponseEntity.status(HttpStatus.OK).body(service.update(member));
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<Member> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

    @PostMapping("/member/signin")
    public ResponseEntity authenticate(@RequestBody MemberDTO dto){
        Member member = service.getByCredentials(dto.getId(), dto.getPassword(), passwordEncoder);
        if(member!=null){ // -> 토큰 생성
            String token = tokenProvider.create(member);
            MemberDTO responseDTO = MemberDTO.builder()
                    .memberNo(member.getMemberNo())
                    .id(member.getId())
                    .name(member.getName())
                    .nickname(member.getNickname())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

}