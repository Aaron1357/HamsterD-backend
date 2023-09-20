<<<<<<< Updated upstream:src/main/java/com/project/hamsterd/domain/Member.java
package com.project.hamsterd.domain;
=======
package com.hamsterD.MemberVo;
>>>>>>> Stashed changes:src/main/java/com/hamsterD/MemberVo/Member.java


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< Updated upstream:src/main/java/com/project/hamsterd/domain/Member.java


//나 금미리 범인이죠
=======
>>>>>>> Stashed changes:src/main/java/com/hamsterD/MemberVo/Member.java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< Updated upstream:src/main/java/com/project/hamsterd/domain/Member.java
=======


>>>>>>> Stashed changes:src/main/java/com/hamsterD/MemberVo/Member.java
public class Member {


    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "memberSQ")
    @SequenceGenerator(name="memberSQ",sequenceName = "SEQ_MEMBER_NO",allocationSize = 1)
    private int memberNo;

    @ManyToOne
    @JoinColumn(name="GROUP_NO")
    @Column(name = "GROUP_NO") // Join필요
    private int groupNo;
    @Column(name = " ID ")
    private String memberId;
    @Column(name = "NAME")
    private String memberName;
    @Column(name = "AGE")
    private int memberAge;
    @Column(name = "ACADEMY_NAME")
    private String academyName;
    @Column(name = "WEIGHT" )
    private int memberWeight;
    @Column(name = "STUDENT_NO")
    private int studentNo;



}
