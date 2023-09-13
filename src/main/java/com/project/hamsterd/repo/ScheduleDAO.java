package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleDAO extends JpaRepository<Schedule, Integer> {

    // 특정 스터디의 모든 스케줄 조회
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE group_no = :groupNo", nativeQuery = true)
    List<Schedule> findByGroupId(int id);

    // 특정 멤버의 모든 스케줄 조회
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE member_no = :memberNo", nativeQuery = true)
    List<Schedule> findByMemberId(int id);




}
