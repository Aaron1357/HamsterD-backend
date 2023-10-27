package com.project.hamsterd.controller;

import com.project.hamsterd.domain.MemberDTO;
import com.project.hamsterd.security.TokenProvider;
import com.project.hamsterd.service.MemberService;
import com.project.hamsterd.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/hamsterd/*")
@Log4j2
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class MemberController {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

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

    @GetMapping("/member/manager")
    public ResponseEntity<List<Member>> getManagerList() {
        log.info("매니저 조회!!");
        return ResponseEntity.status(HttpStatus.OK).body(service.getManagerList());
    }
    @GetMapping("/member/id/{id}")
    public ResponseEntity<Boolean> idDupil(@PathVariable String id) {

        
    // @GetMapping("/member/{id}/{password}")
    // public ResponseEntity<Member> show(@PathVariable String id, @PathVariable String password) {
    //     System.out.println("멤버 개별 조회 들어옴?");



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

    @GetMapping("/member/{id}")
    public ResponseEntity<Member> showMember(@PathVariable String id) {
        Member member = service.showById(id);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @GetMapping("/member/memberno/{memberNo}")
    public ResponseEntity<Member> showMemberbyMemberNO(@PathVariable int memberNo) {
        Member member = service.showMemberbyMemberNO(memberNo);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @PostMapping("/member")
    public ResponseEntity<MemberDTO> create(@RequestBody MemberDTO dto) {


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

        log.info(dto.getAcademyName());
        Member member = Member.builder()

                                .id(dto.getId())
                                .password(passwordEncoder.encode(dto.getPassword()))
                                .name(dto.getName())
                                .birth(dto.getBirth())
                                .gender(dto.getGender())
                                .phone(dto.getPhone())
                                .academyName(dto.getAcademyName())
                                .address(dto.getAddress())
                                .nickname(dto.getNickname())
                                .profile(dto.getProfile())
                                .build();

        Member registerMember = service.create(member);
        log.info("회원가입들어옴");

        MemberDTO responseDTO = MemberDTO.builder()
                .id(registerMember.getId())
                .password(registerMember.getPassword())
                .name(registerMember.getName())
                .birth(registerMember.getBirth())
                .gender(registerMember.getGender())
                .phone(registerMember.getPhone())
                .academyName(registerMember.getAcademyName())
                .address(registerMember.getAddress())
                .nickname(registerMember.getNickname())
                .nickname(registerMember.getProfile())
                .build();

//        log.info(registerMember.toString());


        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    @PutMapping("/member")
    public ResponseEntity<Member> update(MultipartFile profile, @RequestParam(name = "id") String id, @RequestParam(name = "password") String password, @RequestParam(name = "nickname") String nickname) {

        log.info("멤버 id : " + id);
        log.info("멤버 password : " + password);
        log.info("멤버 nickname : " + nickname);


        //1.업로드된 채널 이미지 파일의 원본 파일 이름
        String originalPhoto = profile.getOriginalFilename();

        log.info(originalPhoto);

        //2.마지막 인덱스 값에서 +1 해주면 실제 이름부터 값이 시작됨
        String realPhoto = originalPhoto.substring(originalPhoto.lastIndexOf("\\")+1);
        log.info(realPhoto);

        //3.UUID 무작위로 이름 지정해줌, 파일명 중복 방지위해 사용됨
        String uuid = UUID.randomUUID().toString();

        //4.저장할 채널 이미지파일 경로 구성
        String saveProfile = uploadPath + File.separator + uuid + "_" + realPhoto;
        log.info(saveProfile);

        Member member = Member.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .profile(saveProfile)
                .build();

//        Member vo = new Member();
//        vo.setId(id);
//        vo.setPassword(password);
////        vo.getMember().setNickname(nickname);
//        vo.setNickname(nickname);
//        vo.setProfile(saveProfile);


        Path pathPhoto = Paths.get(saveProfile);
        try {
            profile.transferTo(pathPhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(service.update(member));
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
                    .memberNo(member.getMemberNo())
                    .studyGroup(member.getStudyGroup())
                    .id(member.getId())
                    .name(member.getName())
                    .nickname(member.getNickname())
                    .authority(member.getAuthority())
                    .profile(member.getProfile())
                    .studyGroup(member.getStudyGroup())
                    .token(token)
                    .build();
            log.info(responseDTO);
            return ResponseEntity.ok().body(responseDTO);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }



}