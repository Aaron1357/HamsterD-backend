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
public class PostComment {


    @Id
    @Column(name = "COMMENT_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "commentSequence")
    @SequenceGenerator(name = "commentSequence", sequenceName = "SEQ_COMMENT_NO", allocationSize = 1)
    private int commentNo;


    @Column(name = "IN_COMMENT_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "inCommentSequence")
    @SequenceGenerator(name = "inCommentSequence", sequenceName = "SEQ_IN_COMMENT_NO", allocationSize = 1)
    private int inCommentNo;


    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @Column(name = "CO_CREATEDATE")
    private String createDate;

    @Column(name = "CO_UPDATEDATE")
    private String updateDate;

    @Column(name = "CO_NICKNAME")
    private String nickName;

    @ManyToOne
    @JoinColumn(name = "POST_NO")
    private Post post;

//    @ManyToOne
//    @JoinColumn(name = "MEMBER_NO")
//    private Member member;

}
