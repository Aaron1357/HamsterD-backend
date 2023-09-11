package com.hamsterD.MemberVo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Model {


    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "memberSQ")
    @SequenceGenerator(name="memberSQ",sequenceName = "SEQ_MEMBER_NO",allocationSize = 1)
    private int member_no;

    @ManyToOne
    @JoinColumn(name="GROUP_NO")
    @Column(name = "GROUP_NO") // Join필요
    private int group_no;
    @Column(name = " ID ")
    private String member_id;
    @Column(name = "NAME")
    private String member_name;
    @Column(name = "AGE")
    private int member_age;
    @Column(name = "ACADEMY_NAME")
    private String academy_name;
    @Column(name = "WEIGHT" )
    private int member_weight;
    @Column(name = "STUDENT_NO")
    private int student_no;



}
