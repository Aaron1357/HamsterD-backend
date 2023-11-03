package com.project.hamsterd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Table(name="TB_POST")
public class Post {

    @Id
    @Column(name="post_no")
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="postSequence" )
    @SequenceGenerator(name="postSequence", sequenceName="SEQ_POST_NO", allocationSize=1)
    private int postNo;

    @Column(name="post_title")
    private String postTitle;
    @Column(name="post_content")
    private String postContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+9")
    @Column(name="create_time")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+9")
    @Column(name="update_time")
    private Date updateTime;
    @Column(name="board_view")
    private int boardView;
    @Column
    private String securityCheck;

    @ManyToOne
    @JoinColumn(name="member_no")
     private Member member;
}

