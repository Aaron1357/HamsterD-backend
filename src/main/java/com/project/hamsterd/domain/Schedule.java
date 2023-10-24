package com.project.hamsterd.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@Builder
@Table(name="TB_SCHEDULE")
public class Schedule {

    @Id
    @Column(name="schedule_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="scheduleSequence")
    @SequenceGenerator(name="scheduleSequence", sequenceName="SEQ_SCHEDULE_NO", allocationSize = 1)
    // 스케줄 넘버
    private int scheduleNo;

//    @Column(name="num")
//    // 목록 정렬을 위한 넘버
//    private int num;

    // 스케줄 제목
    @Column(name="schedule_title")
    private String scheduleTitle;

    // 스케줄 내용
    @Column(name="schedule_content")
    private String scheduleContent;

    // 스케줄 날짜
    @Column(name="schedule_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+9")
    private Date scheduleDate;

    // 스터디그룹
    @ManyToOne
    @JoinColumn(name="group_no")
    private StudyGroup studyGroup;

    // 멤버
   @ManyToOne
   @JoinColumn(name="member_no")
   private Member member;

}
