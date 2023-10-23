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
@Table(name="TB_GROUP_COMMENT")
public class GroupComment {

    @Id
    @Column(name = "GROUP_COMMENT_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "groupCommentSequence")
    @SequenceGenerator(name = "groupCommentSequence", sequenceName = "SEQ_GROUP_COMMENT_NO", allocationSize = 1)
    private int gCommentNo;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @Column(name = "CO_CREATEDATE")
    private Date createDate;

    @Column(name = "CO_UPDATEDATE")
    private Date updateDate;

    @Column(name = "CO_NICKNAME")
    private String nickName;

    @ManyToOne
    @JoinColumn(name = "GROUP_NO")
    private StudyGroup studyGroup;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;


}
