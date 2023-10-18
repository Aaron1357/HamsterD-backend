package com.project.hamsterd.service;

import com.project.hamsterd.repo.MemberDAO;
import com.project.hamsterd.repo.StudyGroupDAO;
import com.project.hamsterd.domain.StudyGroup;
import com.project.hamsterd.domain.Schedule;
import com.project.hamsterd.repo.ScheduleDAO;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private StudyGroupDAO studyGroupDAO;

    @Autowired
    private MemberDAO memberDAO;


    // C : 일정 추가
    public Schedule create(Schedule schedule){
        return scheduleDAO.save(schedule);
    }

    // R : 일정 목록 전체 보기
    public List<Schedule> showAll(){
        return scheduleDAO.findAll();
    }

    // R : 일정 1개 상세 보기(scheduleNo로 조회)
    public Schedule show(int groupNo, int scheduleNo){
        Schedule schedule = scheduleDAO.findByGnSn(groupNo, scheduleNo);
        StudyGroup studyGroup = studyGroupDAO.findById(schedule.getStudyGroup().getGroupNo()).orElse(null);
        schedule.setStudyGroup(studyGroup);
        return schedule;
    }

//    public Page<Video> showAll(Pageable pageable, BooleanBuilder builder){ return dao.findAll(builder, pageable); }

    // R : 특정 스터디그룹의 일정 목록 보기(groupNo로 조회)
    public Page<Schedule> showAllGroupSchedule(Pageable pageable, BooleanBuilder builder){
//        return scheduleDAO.findByGroupId(pageable, groupNo); // scheduleDAO에서 관련 쿼리문 생성함
        return scheduleDAO.findAll(builder, pageable);
    }

    // R : 개인 일정 목록(memberNo로 조회)
    public List<Schedule> findByMemberId(int id){
        return scheduleDAO.findByMemberId(id); // scheduleDAO에서 관련 쿼리문 생성함
    }

    // R: 날짜별 일정 목록(scheduleDate로 조회)
    public List<Schedule> findByDate(int groupNo, String scheduleDate){
        return scheduleDAO.findByDate(groupNo, scheduleDate);
    }


    
    // U : 일정 수정
    public Schedule update(Schedule schedule){
        Schedule target = scheduleDAO.findById(schedule.getScheduleNo()).orElse(null);
        log.info("service target: " + target);
        if(target!=null){
            return scheduleDAO.save(schedule);
        }
        return null;
    }

    // D : 일정 삭제
    public Schedule delete(int id){
        Schedule target = scheduleDAO.findById(id).orElse(null);
        scheduleDAO.delete(target);
        return target;
    }

}
