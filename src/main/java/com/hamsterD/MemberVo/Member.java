package com.hamsterD.MemberVo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



//나 금미리 범인이죠
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {


    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "memberSQ")
    @SequenceGenerator(name="memberSQ",sequenceName = "SEQ_MEMBER_NO",allocationSize = 1)
    private int memberNo;

    @ManyToOne
    @JoinColumn(name="GROUP_NO")
    @Column(name = "GROUP_NO") // Join필요
    private int groupNo;
    @Column(name = " ID ")
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
