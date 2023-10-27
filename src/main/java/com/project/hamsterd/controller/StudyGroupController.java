package com.project.hamsterd.controller;

import com.project.hamsterd.domain.GroupEval;
import com.project.hamsterd.domain.Member;
import com.project.hamsterd.domain.Schedule;
import com.project.hamsterd.security.TokenProvider;
import com.project.hamsterd.service.GroupCommentService;
import com.project.hamsterd.service.MemberService;
import com.project.hamsterd.domain.*;
import com.project.hamsterd.service.ScheduleService;
import com.project.hamsterd.service.StudyGroupService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins={"*"}, maxAge = 6000)
@RestController
@RequestMapping("/hamsterd/*")
@Log4j2
public class StudyGroupController {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Autowired
    private StudyGroupService service;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private GroupCommentService gCommentService;

    @Autowired
    private TokenProvider tokenProvider;



    // 스터디 그룹 전체보기
    @GetMapping("/studygroup")
    public ResponseEntity<List<StudyGroup>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }



    // 스터디그룹 개별 조회
    @GetMapping("/studygroup/{groupNo}")
    public ResponseEntity<StudyGroup> show(@PathVariable int groupNo){
        return ResponseEntity.status(HttpStatus.OK).body(service.show(groupNo));
    }


    // 그룹넘버를 기본키로 가지는 스터디 그룹에 속한 멤버들 조회
    @GetMapping("/studygroup/{groupNo}/member")
    public ResponseEntity<List<Member>> showGroupMember(@PathVariable int groupNo){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.inGroup(groupNo));
    }

    // 그룹넘버를 기본키로 가지는 스터디 그룹의 조장 조회
    @GetMapping("/studygroup/manager/{groupNo}")
    public ResponseEntity<Member> findManager(@PathVariable int groupNo){
//        Member mem = memberService.findManager(groupNo);
//        log.info(groupNo);

        return ResponseEntity.status(HttpStatus.OK).body(memberService.findManager(groupNo));
    }




    // 스터디 그룹 생성
    @PostMapping("/studygroup")
    public ResponseEntity<StudyGroup> create(
            @RequestParam("grouptitle") String grouptitle,
            @RequestParam("groupcontent") String groupcontent,
            @RequestParam("groupacademy") String groupacademy,
            @RequestParam("groupimage") MultipartFile groupimage,
            @RequestParam("id") String id)
            {
            log.info(grouptitle);
//        이미지 실제 파일 이름
             String originalImage = groupimage.getOriginalFilename();
             String realImage = originalImage.substring(originalImage.lastIndexOf("\\") + 1);
             log.info("oriImg : " + originalImage);

//        UUID
        String uuid = UUID.randomUUID().toString();

//        실제로 저장할 파일 명 (위치포함)

        String saveImage = uploadPath + File.separator + uuid + "_" + realImage;

        Path pathImage = Paths.get(saveImage);

        try {
            groupimage.transferTo(pathImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

            StudyGroup vo = new StudyGroup();
            vo.setGroupName(grouptitle);
            vo.setGroupContent(groupcontent);
            vo.setGroupAcademy(groupacademy);
            vo.setGroupImage(saveImage);

            StudyGroup studyGroup = service.create(vo);

            Member member = memberService.show(id);
            member.setStudyGroup(studyGroup);
            member.setAuthority("GROUP_MANAGER");
            log.info("controller : " +memberService.update(member).getAuthority());

        return ResponseEntity.status(HttpStatus.OK).body(studyGroup);
    }

    // 스터디그룹 수정
    @PutMapping("/studygroup")
    public ResponseEntity<StudyGroup> update(@RequestBody StudyGroup studyGroup){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(studyGroup));
    }

    // 스터디그룹 가입
    @PutMapping("/studygroup/join")
    public ResponseEntity<Member> joinGroup(@RequestParam("memberNo") int memberNo,
                                                @RequestParam("groupNo") int groupNo){

        Member member = memberService.showMemberbyMemberNO(memberNo);
        StudyGroup vo = service.show(groupNo);
        member.setStudyGroup(vo);
        memberService.update(member);

        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    // 스터디그룹 탈퇴
    @PutMapping("/studygroup/fire")
    public ResponseEntity<Member> fireGroup(@RequestParam("memberNo") int memberNo,
                                            @RequestParam("groupNo") int groupNo){

        Member member = memberService.showMemberbyMemberNO(memberNo);
        StudyGroup vo = service.show(groupNo);
        member.setStudyGroup(null);
        memberService.update(member);

        return ResponseEntity.status(HttpStatus.OK).body(member);
    }


    // 스터디그룹 삭제
    @DeleteMapping("/studygroup/{id}")
    public ResponseEntity<StudyGroup> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

// ============================================================================

    // C :댓글 추가
    @PostMapping("/studyGroup/gcomment")
    public ResponseEntity<GroupComment> create(@RequestParam("newComment") String newComment,
                                               @RequestParam("groupNo") int groupNo,
                                               @RequestParam("token") String token) {
        log.info(groupNo);

        String id = tokenProvider.validateAndGetUserId(token);
        Member member = memberService.showById(id);

        StudyGroup group = new StudyGroup();
        group.setGroupNo(groupNo);

        GroupComment vo = GroupComment.builder()
                    .commentContent(newComment)
                    .studyGroup(group)
                    .member(member)
                    .build();
        log.info(vo.toString());
        return ResponseEntity.status(HttpStatus.OK).body(gCommentService.create(vo));

    }


    // 특정 스터디그룹의 댓글 목록 보기
    @GetMapping("/studyGroup/{groupNo}/gcomment")
    public ResponseEntity<List<GroupComment>> postComment(@PathVariable int groupNo){

        return ResponseEntity.status(HttpStatus.OK).body(gCommentService.findByGroupNo(groupNo));
    }




    //U : 내 댓글 수정하기
    @PutMapping("/studyGroup/gcomment")
    public ResponseEntity <GroupComment> update(@RequestParam("newComment") String newComment,
                                                @RequestParam("groupNo") int groupNo,
                                                @RequestParam("gcommentNo") int gCommentNo,
                                                @RequestParam("token") String token) {
        log.info("groupNo" + groupNo); // 1
        log.info("gCommentNo" + gCommentNo);

        String id = tokenProvider.validateAndGetUserId(token);
        Member member = memberService.showById(id);
        StudyGroup group = service.show(groupNo);

        GroupComment vo = new GroupComment();
        vo.setGCommentNo(gCommentNo);
        vo.setCommentContent(newComment);
        vo.setStudyGroup(group);
        vo.setMember(member);

        log.info("vo : " + vo);


        return ResponseEntity.status(HttpStatus.OK).body(gCommentService.update(vo));
    }


    // D : 댓글 삭제
    @DeleteMapping("/studyGroup/gcomment/{gCommentNo}")
    public ResponseEntity<GroupComment> deletePDelete(@PathVariable int gCommentNo){
        return ResponseEntity.status(HttpStatus.OK).body(gCommentService.delete(gCommentNo));
    }


// ============================================================================

    // C : 일정 추가
    @PostMapping("/schedule")
    public ResponseEntity<Schedule> create(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("date") String dateString,
            @RequestParam("token") String token) {

        log.info("token : " + token);

        String id = tokenProvider.validateAndGetUserId(token);
        Member member = memberService.showById(id);
        log.info(member.toString());

        // 문자열로 받은 날짜를 Date 객체로 변환
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            Schedule vo = Schedule.builder()
                    .scheduleTitle(title)
                    .scheduleContent(content)
                    .scheduleDate(date)
                    .studyGroup(member.getStudyGroup())
                    .member(member)
                    .build();
            log.info(vo.toString());
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.create(vo));
        } catch (ParseException e) {
            // 날짜 파싱 오류 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }





    // R : 일정 조회
    // R : 일정 목록 전체 보기
    @GetMapping("/schedule")
    public ResponseEntity<List<Schedule>> showAllSchedule(){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.showAll());
    }

    // R : 일정 1개 상세 보기(scheduleNo로 조회)
    @GetMapping("/study/{groupNo}/schedule/{scheduleNo}")
    public ResponseEntity<Schedule> showSchdule(@PathVariable int groupNo, @PathVariable int scheduleNo){

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.show(groupNo, scheduleNo));
    }

    // R : 특정 스터디그룹의 일정 목록 보기(목록용)
    @GetMapping("/schedule/study/{groupNo}")
    public ResponseEntity<List<Schedule>> showAllGroupSchedule(@PathVariable int groupNo){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.showAllGroupSchedule(groupNo));
    }


    // R : 개인 일정 목록
    @GetMapping("/schedule/member/{nickname}")
    public ResponseEntity<List<Schedule>> findByMemberId(@PathVariable String nickname){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByMemberId(nickname));
    }

    // R: 일정 날짜별 조회(scheduleDate로 조회)
    @GetMapping("/schedule/study/{groupNo}/{scheduleDate}")
    public ResponseEntity<List<Schedule>> findByDate( @PathVariable int groupNo, @PathVariable String scheduleDate){

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByDate(groupNo, scheduleDate));

    }

    // 제목 검색
    @GetMapping("/study/{groupNo}/{scheduleTitle}")
    public ResponseEntity<List<Schedule>> findByTitle(@PathVariable int groupNo, @PathVariable String scheduleTitle){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByTitle(groupNo, scheduleTitle));

    }

    // 내용 검색
    @GetMapping("/study/{groupNo}/scheduleContent/{scheduleContent}")
    public ResponseEntity<List<Schedule>> findByContent(@PathVariable int groupNo, @PathVariable String scheduleContent){

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByContent(groupNo, scheduleContent));

    }



    // U : 일정 수정
    @PutMapping("/schedule")
    public ResponseEntity<Schedule> updateSchedule(@RequestParam("title") String title,
                                                   @RequestParam("content") String content,
                                                   @RequestParam("date") String dateString,
                                                   @RequestParam("scheduleNo") int scheduleNo,
                                                   @RequestParam("token") String token){


        String id = tokenProvider.validateAndGetUserId(token);
        Member member = memberService.showById(id);

        Schedule vo = new Schedule();

        vo.setScheduleTitle(title);
        vo.setScheduleContent(content);
        vo.setScheduleNo(scheduleNo);
        vo.setStudyGroup(member.getStudyGroup());
        vo.setMember(member);

        // 문자열로 받은 날짜를 Date 객체로 변환
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            vo.setScheduleDate(date);
        } catch (ParseException e) {
            // 날짜 파싱 오류 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(vo));
    }

    // D : 일정 삭제
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.delete(id));
    }


}



