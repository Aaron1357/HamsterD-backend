package com.hamsterD.MemberController;


import com.hamsterD.MemberService.MemberService;
import com.hamsterD.MemberVo.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member/*")

public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("/allmember")
    public ResponseEntity<List<Model>> showAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Model> show(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
    }

    @PostMapping("/createmember")
    public ResponseEntity<Model> create(@RequestBody Model model) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(model));
    }

    @PutMapping("/updatemember")
    public ResponseEntity<Model> update(@RequestBody Model model) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(model));
    }

    @DeleteMapping("/deletemembewr/{id}")
    public ResponseEntity<Model> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

}