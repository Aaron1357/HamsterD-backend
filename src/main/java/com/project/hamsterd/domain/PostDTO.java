package com.project.hamsterd.domain;

import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private int postNo;
    private String postTitle;
    private String postContent;
    private Date createTime;
    private Date updateTime;
    private int boardView;
    private String securityCheck;
    private int totalCount;

    private MemberDTO dto;
}


