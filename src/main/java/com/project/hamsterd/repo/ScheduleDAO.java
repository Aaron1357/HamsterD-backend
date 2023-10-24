package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Schedule;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface ScheduleDAO extends JpaRepository<Schedule, Integer> , QuerydslPredicateExecutor<Schedule> {

    // 스터디그룹 내 일정 1개 상세보기
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE group_no = :groupNo AND schedule_no = :scheduleNo", nativeQuery = true)
    Schedule findByGnSn(@Param("groupNo") int groupNo, @Param("scheduleNo") int scheduleNo);

    // 특정 스터디의 모든 스케줄 조회
    @Query(value="SELECT * FROM TB_SCHEDULE  WHERE group_no = :groupNo ORDER BY schedule_date", nativeQuery = true)
    Page<Schedule> findByGroupId(Pageable pageable, @Param("groupNo") int groupNo);

    // 특정 멤버의 모든 스케줄 조회
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE nickname = :nickname", nativeQuery = true)
    List<Schedule> findByMemberId(@Param("nickname") String nickname);

    // 날짜별 일정 조회
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE group_no = :groupNo AND TO_CHAR(schedule_date, 'YYMMDD') like %:scheduleDate%", nativeQuery = true)
    List<Schedule> findByDate(@Param("groupNo") int groupNo, @Param("scheduleDate") String scheduleDate);

    // 제목으로 검색
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE group_no = :groupNo AND schedule_title like %:scheduleTitle%", nativeQuery = true)
    List<Schedule> findByTitle(@Param("groupNo") int groupNo, @Param("scheduleTitle") String scheduleTitle);

    // 내용으로 검색
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE group_no = :groupNo AND schedule_content like %:scheduleContent%", nativeQuery = true)
    List<Schedule> findByContent(@Param("groupNo") int groupNo, @Param("scheduleContent") String scheduleContent);

}
