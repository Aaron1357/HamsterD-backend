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
@Table(name="TB_COMMENT_LIKE")
public class CommentLike {

    @Id
    @Column(name = "CLIKE_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "likeSequence")
    @SequenceGenerator(name = "likeSequence", sequenceName = "SEQ_COMMENT_LIKE_NO", allocationSize = 1)
    private int cLikeNo;

    @Column(name = "LI_CO_CREATEDATE")
    private String createDate;

    @ManyToOne
    @JoinColumn(name = "COMMENT_NO")
    private PostComment comment;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private  Member member;
}
