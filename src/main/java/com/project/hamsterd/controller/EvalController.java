package com.project.hamsterd.controller;


import com.project.hamsterd.domain.GroupEval;
import com.project.hamsterd.domain.Member;
import com.project.hamsterd.domain.PersonalEval;
import com.project.hamsterd.domain.StudyGroup;
import com.project.hamsterd.service.GroupEvalService;
import com.project.hamsterd.service.MemberService;
import com.project.hamsterd.service.PersonalEvalService;
import com.project.hamsterd.service.StudyGroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hamsterd/*")
@Log4j2
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class EvalController {

    @Autowired
    private GroupEvalService groupEvalService;

    @Autowired
    private PersonalEvalService personalEvalService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private StudyGroupService studyGroupService;


    //    http://localhost:8080/hamsterd/groupeval
    @GetMapping("/groupeval")
    public ResponseEntity<List<GroupEval>> showAllEval(){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.showAll());
    }

    //    http://localhost:8080/hamsterd/groupeval/groupno/1
    @GetMapping("/groupeval/groupno/{groupNo}")
    public ResponseEntity<List<GroupEval>> showGroupEval(@PathVariable int groupNo){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.showGroupEval(groupNo));
    }

    //        http://localhost:8080/hamsterd/groupeval/avg/1
    @GetMapping("/groupeval/avg/{groupNo}")
    public ResponseEntity <Integer> getGroupAVG(@PathVariable int groupNo){
        log.info(groupEvalService.getGroupAVG(groupNo));

        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.getGroupAVG(groupNo));
    }


    //    http://localhost:8080/hamsterd/groupeval/memberno/1
    @GetMapping("/groupeval/memberno/{memberNo}")
    public ResponseEntity<GroupEval> showEval(@PathVariable int memberNo){

        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.show(memberNo));
    }


    //    http://localhost:8080/hamsterd/groupeval
    @PostMapping("/groupeval")
    public ResponseEntity<GroupEval> create(@RequestParam("groupScore") int groupScore,
                                            @RequestParam("review") String review,
                                            @RequestParam("member") int memberNo,
                                            @RequestParam("studyGroup") int groupNo){
        log.info("화긴!");
//        log.info(groupNo);

        GroupEval vo = new GroupEval();
        vo.setGroupScore(groupScore);
        vo.setReview(review);
        Member member = memberService.show(memberNo);
        vo.setMember(member);
        StudyGroup studyGroup = studyGroupService.show(groupNo);
        vo.setStudyGroup(studyGroup);




        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.create(vo));
    }

    //    http://localhost:8080/hamsterd/groupeval
    @PutMapping("/groupeval")
    public ResponseEntity<GroupEval> update(@RequestBody GroupEval groupEval){

        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.update(groupEval));
    }


    //    http://localhost:8080/hamsterd/groupeval/1
    @DeleteMapping("/groupeval/{id}")
    public ResponseEntity<GroupEval> deleteEval(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.delete(id));
    }

    //    http://localhost:8080/hamsterd/groupeval/eval?mno=1&gno=1
    @GetMapping("/groupeval/eval")
    public ResponseEntity<List<GroupEval>> showMemberAndGroup(@RequestParam int mno, @RequestParam int gno){
        return ResponseEntity.status(HttpStatus.OK).body(groupEvalService.showMemberAndGroup(mno, gno));
    }

    //    http://localhost:8080/hamsterd/pereval
    @GetMapping("/pereval")
    public ResponseEntity<List<PersonalEval>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.showAll());
    }

    //    http://localhost:8080/hamsterd/pereval/1
    @GetMapping("/pereval/{id}")
    public ResponseEntity<PersonalEval> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.show(id));
    }

    //    http://localhost:8080/hamsterd/pereval
    @PostMapping("/pereval")
    public ResponseEntity<PersonalEval> create(@RequestBody PersonalEval perEval){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.create(perEval));
    }


//    http://localhost:8080/hamsterd/pereval
    @PutMapping("/pereval")
    public ResponseEntity<PersonalEval> update(@RequestBody PersonalEval perEval){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.update(perEval));
    }

//    http://localhost:8080/hamsterd/pereval/1
    @DeleteMapping("/pereval/{id}")
    public ResponseEntity<PersonalEval> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.delete(id));
    }


//    http://localhost:8080/hamsterd/pereval/eval?no=1
    @GetMapping("/pereval/eval")
    public ResponseEntity<List<PersonalEval>> showMember(@RequestParam int no){
        return ResponseEntity.status(HttpStatus.OK).body(personalEvalService.showMemberEval(no));
    }


}
