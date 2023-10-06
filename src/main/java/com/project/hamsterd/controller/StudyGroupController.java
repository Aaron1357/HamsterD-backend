package com.project.hamsterd.controller;

import com.project.hamsterd.domain.GroupEval;
import com.project.hamsterd.domain.Member;
import com.project.hamsterd.domain.Schedule;
import com.project.hamsterd.service.GroupEvalService;
import com.project.hamsterd.service.ScheduleService;
import com.project.hamsterd.service.StudyGroupService;
import com.project.hamsterd.domain.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/hamsterd/*")
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
    public ResponseEntity<Schedule> create(@RequestBody Schedule schedule){

        Member member = new Member();
        member.setMemberNo(1);

        schedule.setMember(member);

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.create(schedule));
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
    public ResponseEntity<List<Schedule>> showAllGroupSchedule(@PathVariable int groupNo){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.showAllGroupSchedule(groupNo));
    }

    // R : 개인 일정 목록(memberNo로 조회)
    @GetMapping("/schedule/member/{memberNo}")
    public ResponseEntity<List<Schedule>> findByMemberId(@PathVariable int memberNo){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByMemberId(memberNo));
    }

    // R: 일정 날짜별 조회(scheduleDate로 조회)
//    @GetMapping("/study/{groupNo}/{scheduleDate}")
//    public ResponseEntity<List<Schedule>> findByDate(@PathVariable int groupNo, @PathVariable String scheduleDate){
//
//        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByDate(groupNo, scheduleDate));
//
//    }

    // R: 일정 날짜별 조회(scheduleDate로 조회)
    @GetMapping("/study/{scheduleDate}")
    public ResponseEntity<List<Schedule>> findByDate(@PathVariable String scheduleDate){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByDate(scheduleDate));
    }


    // U : 일정 수정
    @PutMapping("/schedule")
    public ResponseEntity<Schedule> updateSchedule(@RequestBody Schedule schedule){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(schedule));
    }

    // D : 일정 삭제
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.delete(id));
    }


}



