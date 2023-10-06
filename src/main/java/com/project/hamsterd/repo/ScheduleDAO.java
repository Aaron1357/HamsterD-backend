package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleDAO extends JpaRepository<Schedule, Integer> {

    // 스터디그룹 내 일정 1개 상세보기
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE group_no = :groupNo AND schedule_no = :scheduleNo", nativeQuery = true)
    Schedule findByGnSn(@Param("groupNo") int groupNo, @Param("scheduleNo") int scheduleNo);

    // 특정 스터디의 모든 스케줄 조회
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE group_no = :groupNo", nativeQuery = true)
    List<Schedule> findByGroupId(@Param("groupNo") int groupNo);

    // 특정 멤버의 모든 스케줄 조회
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE member_no = :memberNo", nativeQuery = true)
    List<Schedule> findByMemberId(@Param("memberNo") int memberNo);

    // 날짜별 일정 조회
//    @Query(value="SELECT * FROM TB_SCHEDULE WHERE group_no = :groupNo AND schedule_date like '% :scheduleDate %'", nativeQuery = true) // like써서 날짜 포함하는 경우, 날짜비교하는 쿼리문
//    List<Schedule> findByDate(@Param("groupNo") int groupNo, @Param("scheduleDate") String scheduleDate);

    // 날짜별 일정 조회
    @Query(value="SELECT * FROM TB_SCHEDULE WHERE schedule_date like %:scheduleDate%", nativeQuery = true) // like써서 날짜 포함하는 경우, 날짜비교하는 쿼리문
    List<Schedule> findByDate(@Param("scheduleDate") String scheduleDate);





}
