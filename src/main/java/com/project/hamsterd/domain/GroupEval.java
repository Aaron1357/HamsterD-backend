package com.project.hamsterd.domain;

import com.hamsterD.MemberVo.Model;
import com.hamsterD.StudyGroupVO.StudyGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupEval {

    @Id
    @Column(name = "GROUP_REV_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "groupRevSequence")
    @SequenceGenerator(name = "groupRevSequence", sequenceName = "SEQ_GROUP_REV_NO", allocationSize = 1)
    private int groupRevNo;

    @Column(name = "GROUP_SCORE")
    private int groupScore;

    @Column
    private String review;

    @Column(name = "FOOT_PRINT")
    private String footPrint;

    @Column
    private int penalty;

    @ManyToOne
    @JoinColumn(name = "GROUP_NO")
    private StudyGroup studyGroup;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Model model;

}
