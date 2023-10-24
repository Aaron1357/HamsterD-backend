package com.project.hamsterd.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Table(name="TB_STUDYGROUP")
public class StudyGroup {

    @Id
    @Column(name = "GROUP_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "groupSQ")
    @SequenceGenerator(name="groupSQ",sequenceName = "SEQ_GROUP_NO",allocationSize = 1)
    private int groupNo;

    @Column
    private String groupName;

    @Column
    private String groupContent;

    @Column
    private String groupAcademy;

    @Column
    private String groupImage;

   }
