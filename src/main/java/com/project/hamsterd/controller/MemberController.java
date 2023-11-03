package com.project.hamsterd.controller;

import com.project.hamsterd.domain.MemberDTO;
import com.project.hamsterd.security.TokenProvider;
import com.project.hamsterd.service.MemberService;
import com.project.hamsterd.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
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
        // 아이디 중복확인
        Member member = service.findById(id);

        if(member != null){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }

        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @GetMapping("/member/nick/{nickName}")
    public ResponseEntity<Boolean> nickDupil(@PathVariable String nickName) {
        // 닉네임 중복확인
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
        // 회원가입
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
                                .email(dto.getEmail())
                                .profile(dto.getProfile())
                                .build();

        Member registerMember = service.create(member);
        



        MemberDTO responseDTO = service.makeToken(registerMember, tokenProvider);


        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    @PutMapping("/member")
    public ResponseEntity<MemberDTO> update(MultipartFile profile, @RequestParam(name = "id") String id, @RequestParam(name = "password") String password, @RequestParam(name = "nickname") String nickname) {

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


        Path pathPhoto = Paths.get(saveProfile);
        try {
            profile.transferTo(pathPhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Member resultMem = service.update(member);

        if(resultMem != null){
            MemberDTO responseDTO = service.makeToken(resultMem, tokenProvider);
            log.info(responseDTO);
            return ResponseEntity.ok().body(responseDTO);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<Member> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

    @PostMapping("/member/signin")
    public ResponseEntity<MemberDTO> authenticate(@RequestBody MemberDTO dto){

        Member member = service.getByCredentials(dto.getId(), dto.getPassword(), passwordEncoder);

        log.info(member);
        if(member!=null){ // -> 토큰 생성
            MemberDTO responseDTO = service.makeToken(member, tokenProvider);
            log.info(responseDTO);
            return ResponseEntity.ok().body(responseDTO);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }



}