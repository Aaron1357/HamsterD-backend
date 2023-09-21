package com.project.hamsterd.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name="TB_PER_REV")
public class PersonalEval {

    @Id
    @Column(name = "REV_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "revNoSequence")
    @SequenceGenerator(name = "revNoSequence", sequenceName = "SEQ_PER_REV_NO", allocationSize = 1)
    private int revNo;  // 리뷰번호

    @Column(name = "PENALTY")
    private int penalty;    // 패널티

    @Column(name = "FOOT_PRINT")
    private int footPrint;  // 리뷰점수


    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;  // 멤버 테이블 조인
}
