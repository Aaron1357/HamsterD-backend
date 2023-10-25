package com.project.hamsterd.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_GROUP_REV")
public class GroupEval {

    @Id
    @Column(name = "GROUP_REV_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "groupRevSequence")
    @SequenceGenerator(name = "groupRevSequence", sequenceName = "SEQ_GROUP_REV_NO", allocationSize = 1)
    private int groupRevNo;

    @Column(name = "GROUP_SCORE")
    private int groupScore; // 그룹 평가 점수( 나중에 평균내서 점수 출력시키기)

    @Column(name = "REVIEW")
    private String review;  // 그룹 평가내용

    @ManyToOne
    @JoinColumn(name = "GROUP_NO")
    private StudyGroup studyGroup;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

}
