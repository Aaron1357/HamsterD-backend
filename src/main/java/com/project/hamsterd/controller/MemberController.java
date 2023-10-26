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

    @GetMapping("/member/id/{id}")
    public ResponseEntity<Boolean> idDupil(@PathVariable String id) {



        System.out.println("멤버 개별 조회 들어옴?(아이디)");
        System.out.println("id : " + id);



        Member member = service.findById(id);

        if(member != null){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }

        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @GetMapping("/member/nick/{nickName}")
    public ResponseEntity<Boolean> nickDupil(@PathVariable String nickName) {



        System.out.println("멤버 개별 조회 들어옴?(닉네임)");
        System.out.println("nickName : " + nickName);



        Member member = service.findByNick(nickName);

        if(member != null){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }

        return ResponseEntity.status(HttpStatus.OK).body(false);
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
    
        log.info(dto.getAcademy());
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
        log.info("회원가입들어옴");
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
    public ResponseEntity<MemberDTO> update(MultipartFile profile, @RequestParam(name = "id") String id, @RequestParam(name = "password") String password, @RequestParam(name = "nickname") String nickname) {
        Member member = Member.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .build();
        log.info(service.update(member));

        Member mem = service.update(member);

        String token = tokenProvider.create(mem);

        log.info(mem.getNickname());

        MemberDTO responseDTO = MemberDTO.builder()
                .id(mem.getId())
                .name(mem.getName())
                .nickname(mem.getNickname())
                .authority(mem.getAuthority())
                .token(token)
                .build();
        System.out.println("회원정보수정");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<Member> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

    @PostMapping("/member/signin")
    public ResponseEntity<MemberDTO> authenticate(@RequestBody MemberDTO dto){
        Member member = service.getByCredentials(dto.getId(), dto.getPassword(), passwordEncoder);
        if(member!=null){ // -> 토큰 생성
            String token = tokenProvider.create(member);
            MemberDTO responseDTO = MemberDTO.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .nickname(member.getNickname())
                    .authority(member.getAuthority())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

}