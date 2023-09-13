package com.hamsterD.StudyGroupController;

import com.hamsterD.StudyGroupService.StudyGroupService;
import com.hamsterD.StudyGroupVO.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hamsterd/*")
public class StudyGroupController {

    @Autowired
    private StudyGroupService service;

    @GetMapping("/studygroup")
    public ResponseEntity<List<StudyGroup>> showAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    @GetMapping("/studygroup/{id}")
    public ResponseEntity<StudyGroup> show(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
    }

    @PostMapping("/studygroup")
    public ResponseEntity<StudyGroup> create(@RequestBody StudyGroup studyGroup){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(studyGroup));
    }

    @PutMapping("/studygroup")
    public ResponseEntity<StudyGroup> update(@RequestBody StudyGroup studyGroup){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(studyGroup));
    }

    @DeleteMapping("/studygroup/{id}")
    public ResponseEntity<StudyGroup> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}



