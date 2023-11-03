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
@Builder
@Table(name="TB_COMMENT")
public class PostComment {


    @Id
    @Column(name = "COMMENT_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "commentSequence")
    @SequenceGenerator(name = "commentSequence", sequenceName = "SEQ_COMMENT_NO", allocationSize = 1)
    private int commentNo;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "CO_CREATEDATE")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "CO_UPDATEDATE")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "POST_NO")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

}
