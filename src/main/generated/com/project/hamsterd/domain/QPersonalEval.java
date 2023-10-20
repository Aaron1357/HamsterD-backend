package com.project.hamsterd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersonalEval is a Querydsl query type for PersonalEval
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonalEval extends EntityPathBase<PersonalEval> {

    private static final long serialVersionUID = 1633694812L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalEval personalEval = new QPersonalEval("personalEval");

    public final NumberPath<Integer> footPrint = createNumber("footPrint", Integer.class);

    public final QMember member;

    public final NumberPath<Integer> penalty = createNumber("penalty", Integer.class);

    public final NumberPath<Integer> revNo = createNumber("revNo", Integer.class);

    public QPersonalEval(String variable) {
        this(PersonalEval.class, forVariable(variable), INITS);
    }

    public QPersonalEval(Path<? extends PersonalEval> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPersonalEval(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPersonalEval(PathMetadata metadata, PathInits inits) {
        this(PersonalEval.class, metadata, inits);
    }

    public QPersonalEval(Class<? extends PersonalEval> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

