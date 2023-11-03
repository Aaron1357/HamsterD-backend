package com.project.hamsterd.domain;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String token;
    private int memberNo;
    private String id;
    private String password;
    private String name;
    private Date birth;
    private String gender;
    private String phone;
    private String address;
    private String academyName;
    private String authority;
    private String nickname;
    private String email;
    private String profile;
    private StudyGroup studyGroup;

}
