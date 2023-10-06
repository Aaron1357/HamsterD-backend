package com.project.hamsterd.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    @Column(name="create_time")
    private Date createTime;
    @Column(name="update_time")
    private Date updateTime;
    @Column(name="post_file")
    private String postFile;


    /*공지글 여부 할지말지 고민중*/
    /*private Boolean noticeYn;*/
    
    //멤버에서 닉네임 받아와야함
    @ManyToOne
    @JoinColumn(name="member_no")
     /*멤버 클래스의 멤버 넘버 외래키 */
     private Member member;
}

