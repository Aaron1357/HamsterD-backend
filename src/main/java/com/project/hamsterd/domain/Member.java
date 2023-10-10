package com.project.hamsterd.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name="TB_MEMBER")
public class Member {


    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "memberSEQ")
    @SequenceGenerator(name="memberSEQ",sequenceName = "SEQ_MEMBER_NO", allocationSize = 1)
    private int memberNo;


    @Column(name = "ID")
    private String id;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTH")
    private Date birth;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDR")
    private String address;

    @Column(name = "ACADEMY_NAME")
    private String academyName;

    @Column(name = "WEIGHT" )
    private int weight;



    @Column(name = "AUTHORITY")
    private String authority;
    @Column(name ="NICNAME")
    private String nickname;

    @Column(name = "NICKNAME")
    private String nickname;

    @ManyToOne
    @JoinColumn(name="GROUP_NO")
    private StudyGroup studyGroup;

}
