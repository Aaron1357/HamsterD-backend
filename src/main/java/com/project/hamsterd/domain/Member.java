package com.project.hamsterd.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Member {

    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "memberSEQ")
    @SequenceGenerator(name="memberSEQ",sequenceName = "SEQ_MEMBER_NO", allocationSize = 1)
    private int memberNo;

    @ManyToOne
    @JoinColumn(name="GROUP_NO")
    private StudyGroup studyGroup;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private Date birth;

    @Column
    private String gender;

    @Column
    private String phone;

    @Column(name = "ADDR")
    private String address;

    @Column(name = "ACADEMY_NAME")
    private String academyName;

    @Column
    private int weight;


    @Column
    private String authority;


    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String profile;


}
