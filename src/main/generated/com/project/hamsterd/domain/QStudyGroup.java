package com.project.hamsterd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyGroup is a Querydsl query type for StudyGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyGroup extends EntityPathBase<StudyGroup> {

    private static final long serialVersionUID = -520745066L;

    public static final QStudyGroup studyGroup = new QStudyGroup("studyGroup");

    public final StringPath groupAcademy = createString("groupAcademy");

    public final StringPath groupContent = createString("groupContent");

    public final StringPath groupImage = createString("groupImage");

    public final StringPath groupName = createString("groupName");

    public final NumberPath<Integer> groupNo = createNumber("groupNo", Integer.class);

    public QStudyGroup(String variable) {
        super(StudyGroup.class, forVariable(variable));
    }

    public QStudyGroup(Path<? extends StudyGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyGroup(PathMetadata metadata) {
        super(StudyGroup.class, metadata);
    }

}

