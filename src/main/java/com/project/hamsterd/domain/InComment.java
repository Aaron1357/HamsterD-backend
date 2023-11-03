package com.project.hamsterd.domain;

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
@Builder
@Table(name="TB_IN_COMMENT")
public class InComment {

    @Id
    @Column(name = "IN_COMMENT_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "inCommentSequence")
    @SequenceGenerator(name = "inCommentSequence", sequenceName = "SEQ_IN_COMMENT_NO", allocationSize = 1)
    //대댓글 번호
    private int inCoNo;

    //대댓글
    @Column(name = "IN_COMMENT_CONTENT")
    private String inCoCon;

    //대댓글 작성시간
    @Column(name = "IN_CO_CREATEDATE")
    private Date createDate;

    //대댓글 수정시간
    @Column(name = "IN_CO_UPDATEDATE")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "POST_NO")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "COMMENT_NO")
    private PostComment postComment;

}
