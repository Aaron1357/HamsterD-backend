package com.project.hamsterd.service;

import com.project.hamsterd.domain.Schedule;
import com.project.hamsterd.repo.ScheduleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;


    // C : 일정 추가
    public Schedule create(Schedule schedule){
        return scheduleDAO.save(schedule);
    }

    // R : 일정 목록 전체 보기
    public List<Schedule> showAll(){
        return scheduleDAO.findAll();
    }

    // R : 일정 1개 상세 보기(변수 및 타입 수정 필요함)
//    public Schedule show(int id){
//        Schedule schedule = scheduleDAO.findById(id).orElse(null);
//        StudyGroup studyGroup = studyGroupDAO.findById(schedule.getStudyGroup().getGroupNo()).orElse(null);
//        schedule.setStudyGroup(studyGroup);
//        return schedule;
//    }
    
    // R : 특정 스터디그룹의 일정 목록 보기

    // R : 개인 일정 전체 보기
    
    // R : 개인 일정 1개 상세 보기

    // U : 일정 수정
    public Schedule update(Schedule schedule){
        Schedule target = scheduleDAO.findById(schedule.getScheduleNo()).orElse(null);

        if(target!=null){
            return scheduleDAO.save(schedule);
        }
        return null;
    }

    // D : 일정
    public Schedule delete(int id){
        Schedule target = scheduleDAO.findById(id).orElse(null);
        scheduleDAO.delete(target);
        return target;
    }
}
