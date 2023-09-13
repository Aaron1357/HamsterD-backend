package com.project.hamsterd.domain;

import jakarta.persistence.*;

import java.util.Date;

public class PostLike {

    @Id
    @Column(name="like_no")
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="postLikeSequence" )
    @SequenceGenerator(name="postLikeSequence", sequenceName="SEQ_POSTLIKE", allocationSize=1)
    private int likeNo;


    @Column(name="post_like_createtime")
    /*좋아요 누른 시간 POST_LIKE_CREATETIME*/
    private Date postLCT;

    @ManyToOne
    @JoinColumn(name="post_no")
    /*post_no fk*/
    private Post post;

    @ManyToOne
    @JoinColumn(name="member_no")
    /*member_no fk*/
    private Member member;
}
