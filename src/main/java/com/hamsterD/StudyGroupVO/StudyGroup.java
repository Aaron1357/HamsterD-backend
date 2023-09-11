package com.hamsterD.StudyGroupVO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class StudyGroup {

    @Id
    @Column(name = "GROUP_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "groupSQ")
    @SequenceGenerator(name="groupSQ",sequenceName = "SEQ_GROUP_NO",allocationSize = 1)
    private int group_no;

    @Column
    private String academy;

    @Column
    private String groupname;

    @Column
    private int membercount;
}
