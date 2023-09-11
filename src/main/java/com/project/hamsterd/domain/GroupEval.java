package com.project.hamsterd.domain;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "groupRevSeqeunce")
    @SequenceGenerator(name = "groupRevSeqeunce", sequenceName = "SEQ_GROUP_REV_NO", allocationSize = 1)
    private int groupRevNo;

    @Column(name = "GROUP_SCORE")
    private int groupScore;

    @Column(name = "REVIEW")
    private String review;

    @Column(name = "FOOT_PRINT")
    private String footPrint;

    @Column(name = "PENALTY")
    private int penalty;

    @ManyToOne
    @JoinColumn(name = "GROUP_NO")
    private int groupNo;

    @ManyToOne
    @JoinColumn(name = "MEMEBER_NO")
    private Member member;

}
