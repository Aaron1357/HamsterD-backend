package com.hamsterD.MemberVo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Model {


    @Id
    private int member_no;
    @Column
    private int group_no;
    @Column
    private String member_id;
    @Column
    private String member_name;
    @Column
    private int member_age;
    @Column
    private String academy_name;
    @Column
    private int member_weight;
    @Column
    private int student_no;



}
