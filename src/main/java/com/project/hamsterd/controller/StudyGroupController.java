package com.project.hamsterd.controller;

import com.project.hamsterd.domain.*;
import com.project.hamsterd.service.ScheduleService;
import com.project.hamsterd.service.StudyGroupService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins={"*"}, maxAge = 6000)
@RestController
@RequestMapping("/hamsterd/*")
@Log4j2
public class StudyGroupController {

    @Autowired
    private StudyGroupService service;

    @Autowired
    private ScheduleService scheduleService;

    // 스터디 그룹 전체보기
    @GetMapping("/studygroup")
    public ResponseEntity<List<StudyGroup>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    // 스터디그룹 개별 조회
    @GetMapping("/studygroup/{id}")
    public ResponseEntity<StudyGroup> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
    }

    // 스터디 그룹 생성
    @PostMapping("/studygroup")
    public ResponseEntity<StudyGroup> create(@RequestBody StudyGroup studyGroup){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(studyGroup));
    }

    // 스터디그룹 수정
    @PutMapping("/studygroup")
    public ResponseEntity<StudyGroup> update(@RequestBody StudyGroup studyGroup){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(studyGroup));
    }

    // 스터디그룹 삭제
    @DeleteMapping("/studygroup/{id}")
    public ResponseEntity<StudyGroup> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

    // C : 일정 추가
    @PostMapping("/schedule")
    public ResponseEntity<Schedule> create(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("date") String dateString) {

        Schedule vo = new Schedule();
        vo.setScheduleTitle(title);
        vo.setScheduleContent(content);

        StudyGroup sg = new StudyGroup();
        sg.setGroupNo(1);

        Member m = new Member();
        m.setMemberNo(2);

        vo.setStudyGroup(sg);
        vo.setMember(m);

        // 문자열로 받은 날짜를 Date 객체로 변환
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            vo.setScheduleDate(date);
        } catch (ParseException e) {
            // 날짜 파싱 오류 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.create(vo));
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

    // R : 특정 스터디그룹의 일정 목록 보기
    @GetMapping("/schedule/study/{groupNo}")
    public ResponseEntity<List<Schedule>> showAllGroupSchedule(@RequestParam(name="page", defaultValue = "1") int page, @PathVariable int groupNo){
        Sort sort = Sort.by("scheduleNo").descending();
        Pageable pageable = PageRequest.of(page-1, 20, sort); // 시작페이지(0부터 시작), 몇개씩 보여줄지

        QSchedule qSchedule = QSchedule.schedule;

        BooleanBuilder builder = new BooleanBuilder();

        if(groupNo!=0) {
            BooleanExpression expression = qSchedule.studyGroup.groupNo.eq(groupNo);
            builder.and(expression);
        }

        Page<Schedule> result = scheduleService.showAllGroupSchedule(pageable, builder);

//        Page<Schedule> result = scheduleService.showAllGroupSchedule(pageable, groupNo);
        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
//        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // R : 개인 일정 목록(memberNo로 조회)
    @GetMapping("/schedule/member/{memberNo}")
    public ResponseEntity<List<Schedule>> findByMemberId(@PathVariable int memberNo){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByMemberId(memberNo));
    }

    // R: 일정 날짜별 조회(scheduleDate로 조회)
    @GetMapping("/schedule/study/{groupNo}/{scheduleDate}")
    public ResponseEntity<List<Schedule>> findByDate(@PathVariable int groupNo, @PathVariable String scheduleDate){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByDate(groupNo, scheduleDate));

    }




    // U : 일정 수정
    @PutMapping("/schedule")
    public ResponseEntity<Schedule> updateSchedule(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("date") String dateString, @RequestParam("scheduleNo") int scheduleNo){
        // ResponseEntity<Schedule> schedule = showSchdule(1, 1);

        // 수정한 값으로 잘 들어옴
        log.info("title : " + title);
        log.info("date : " + dateString);
        log.info("content : " + content);
        log.info("scheduleNo : " + scheduleNo);



        Schedule vo = new Schedule();

        StudyGroup sg = new StudyGroup();
        sg.setGroupNo(1);

        Member m = new Member();
        m.setMemberNo(1);


        vo.setScheduleTitle(title);
        vo.setScheduleContent(content);
        vo.setScheduleNo(scheduleNo);
        vo.setStudyGroup(sg);
        vo.setMember(m);

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



