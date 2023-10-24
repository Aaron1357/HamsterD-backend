package com.project.hamsterd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupComment is a Querydsl query type for GroupComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupComment extends EntityPathBase<GroupComment> {

    private static final long serialVersionUID = -1801184192L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupComment groupComment = new QGroupComment("groupComment");

    public final StringPath commentContent = createString("commentContent");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> gCommentNo = createNumber("gCommentNo", Integer.class);

    public final QMember member;

    public final StringPath nickName = createString("nickName");

    public final QStudyGroup studyGroup;

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QGroupComment(String variable) {
        this(GroupComment.class, forVariable(variable), INITS);
    }

    public QGroupComment(Path<? extends GroupComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupComment(PathMetadata metadata, PathInits inits) {
        this(GroupComment.class, metadata, inits);
    }

    public QGroupComment(Class<? extends GroupComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.studyGroup = inits.isInitialized("studyGroup") ? new QStudyGroup(forProperty("studyGroup")) : null;
    }

}

