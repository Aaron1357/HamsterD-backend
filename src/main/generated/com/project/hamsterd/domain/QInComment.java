package com.project.hamsterd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInComment is a Querydsl query type for InComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInComment extends EntityPathBase<InComment> {

    private static final long serialVersionUID = -447035366L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInComment inComment = new QInComment("inComment");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath inCoCon = createString("inCoCon");

    public final NumberPath<Integer> inCoNo = createNumber("inCoNo", Integer.class);

    public final QMember member;

    public final QPost post;

    public final QPostComment postComment;

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QInComment(String variable) {
        this(InComment.class, forVariable(variable), INITS);
    }

    public QInComment(Path<? extends InComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInComment(PathMetadata metadata, PathInits inits) {
        this(InComment.class, metadata, inits);
    }

    public QInComment(Class<? extends InComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
        this.postComment = inits.isInitialized("postComment") ? new QPostComment(forProperty("postComment"), inits.get("postComment")) : null;
    }

}

