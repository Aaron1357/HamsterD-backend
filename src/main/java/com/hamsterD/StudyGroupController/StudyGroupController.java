package com.hamsterD.StudyGroupController;

import com.hamsterD.StudyGroupService.StudyGroupService;
import com.hamsterD.StudyGroupVO.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studygroup/*")
public class StudyGroupController {

    @Autowired
    private StudyGroupService service;

    @GetMapping("/allreview")
    public ResponseEntity<List<StudyGroup>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<StudyGroup> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
    }

    @PostMapping("/createreview")
    public ResponseEntity<StudyGroup> create(@RequestBody StudyGroup studyGroup){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(studyGroup));
    }

    @PutMapping("/updatereview")
    public ResponseEntity<StudyGroup> update(@RequestBody StudyGroup studyGroup){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(studyGroup));
    }

    @DeleteMapping("/deletereview/{id}")
    public ResponseEntity<StudyGroup> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}



