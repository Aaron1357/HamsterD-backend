package com.project.hamsterd.domain;


import com.hamsterD.MemberVo.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalEval {

    @Id
    @Column(name = "REV_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "revNoSequence")
    @SequenceGenerator(name = "revNoSequence", sequenceName = "SEQ_PER_REV_NO", allocationSize = 1)
    private int revNo;

    @Column(name = "PENALTY")
    private int penalty;

    @Column(name = "FOOT_PRINT")
    private int footPrint;


    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Model member;
}
