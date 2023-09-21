
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
@Table(name="TB_MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "memberSQ")
    @SequenceGenerator(name="memberSQ",sequenceName = "SEQ_MEMBER_NO",allocationSize = 1)
    private int memberNo;

    @ManyToOne
    @JoinColumn(name="GROUP_NO")
    private StudyGroup studyGroup;
    @Column(name = "ID")
    private String memberId;
    @Column(name = "NAME")
    private String memberName;
    @Column(name = "AGE")
    private int memberAge;
    @Column(name = "ACADEMY_NAME")
    private String academyName;
    @Column(name = "WEIGHT" )
    private int memberWeight;
    @Column(name = "STUDENT_NO")
    private int studentNo;



}
