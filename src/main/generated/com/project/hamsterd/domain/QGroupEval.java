package com.project.hamsterd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupEval is a Querydsl query type for GroupEval
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupEval extends EntityPathBase<GroupEval> {

    private static final long serialVersionUID = 2118006363L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupEval groupEval = new QGroupEval("groupEval");

    public final NumberPath<Integer> groupRevNo = createNumber("groupRevNo", Integer.class);

    public final NumberPath<Integer> groupScore = createNumber("groupScore", Integer.class);

    public final QMember member;

    public final StringPath review = createString("review");

    public final QStudyGroup studyGroup;

    public QGroupEval(String variable) {
        this(GroupEval.class, forVariable(variable), INITS);
    }

    public QGroupEval(Path<? extends GroupEval> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupEval(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupEval(PathMetadata metadata, PathInits inits) {
        this(GroupEval.class, metadata, inits);
    }

    public QGroupEval(Class<? extends GroupEval> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.studyGroup = inits.isInitialized("studyGroup") ? new QStudyGroup(forProperty("studyGroup")) : null;
    }

}

