package com.project.hamsterd.controller;

import com.project.hamsterd.domain.Schedule;
import com.project.hamsterd.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hamsterd/*")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;


    // C : 일정 추가
    @PostMapping("/schedule")
    public ResponseEntity<Schedule> create(@RequestBody Schedule schedule){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.create(schedule));
    }
    
    // R : 일정 조회
    // R : 일정 목록 전체 보기
    @GetMapping("/schedule")
    public ResponseEntity<List<Schedule>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.showAll());
    }
    // R : 일정 1개 상세 보기(scheduleNo로 조회)
    @GetMapping("/schedule/{id}")
    public ResponseEntity<Schedule> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.show(id));
    }

    // R : 특정 스터디그룹의 일정 목록 보기
    @GetMapping("/schedule/{groupNo}")
    public ResponseEntity<List<Schedule>> showAllGroupSchedule(@PathVariable int groupNo){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.showAllGroupSchedule(groupNo));
    }

    // R : 개인 일정 전체 보기(memberNo로 조회)
    @GetMapping("schedule/{memberNo}")
    public ResponseEntity<List<Schedule>> showAllMemberSchedule(@PathVariable int memberNo){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.showAllMemberSchedule(memberNo));
    }

    // R : 개인 일정 1개 상세 보기 (memberNo 외에 매개변수 받아올 것 정해서 다시 짜기)


    // U : 일정 수정
    @PutMapping("/schedule")
    public ResponseEntity<Schedule> update(@RequestBody Schedule schedule){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(schedule));
    }

    // D : 일정 삭제
    @DeleteMapping("/schedule")
    public ResponseEntity<Schedule> delete(@RequestBody int id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.delete(id));
    }
}
