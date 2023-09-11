package com.project.hamsterd.domain;


import com.hamsterD.MemberVo.Member;
import com.hamsterD.StudyGroupVO.StudyGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
public class Schedule {

    @Id
    @Column(name="schedule_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="")
    @SequenceGenerator(name="scheduleSequence", sequenceName="SEQ_SCHEDULE", allocationSize = 1)
    // 스케줄 넘버
    private int scheduleNo;

    // 스케줄 제목
    @Column(name="schedule_title")
    private String scheduleTitle;

    // 스케줄 내용
    @Column(name="schedule_content")
    private String scheduleContent;

    // 스케줄 날짜
    @Column(name="schedule_date")
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
