package com.project.hamsterd.controller;

import com.project.hamsterd.domain.InComment;
import com.project.hamsterd.domain.Member;
import com.project.hamsterd.domain.Post;
import com.project.hamsterd.domain.PostComment;
import com.project.hamsterd.service.InCommentService;
import com.project.hamsterd.service.PostCommentService;
import com.project.hamsterd.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/hamsterd/*")
//postman과 연동시켜주기 위한 애노테이션 테스트위한거임
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class PostController {
    @Autowired
    private PostService service;

    @Autowired
    private InCommentService iCommentService;

    @Autowired
    private PostCommentService pCommentService;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;


    //C : 게시판 작성하기
    @PostMapping("/post")
    public ResponseEntity <Post> create(@RequestParam("title") String title, @RequestParam("desc") String desc,  @RequestParam("file") MultipartFile file) {


        //1.업로드된 채널 이미지 파일의 원본 파일 이름
        String originalPhoto = file.getOriginalFilename();

        //2.마지막 인덱스 값에서 +1 해주면 실제 이름부터 값이 시작됨
        String realPhoto = originalPhoto.substring(originalPhoto.lastIndexOf("\\")+1);

        //3.UUID 무작위로 이름 지정해줌, 파일명 중복 방지위해 사용됨
        String uuid = UUID.randomUUID().toString();

        //4.저장할 채널 이미지파일 경로 구성
        String savePhoto = uploadPath + File.separator + uuid + "_" + realPhoto;

        Post vo = new Post();
        vo.setPostTitle(title);
        vo.setPostContent(desc);
//        vo.getMember().setNickname(nickname);
        vo.setPostFile(savePhoto);


        Path pathPhoto = Paths.get(savePhoto);
        try {
            file.transferTo(pathPhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //  postman 테스트해보기 위한 코드
        // return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.OK).body(service.create(vo));
    }

    //C : 관리자 공지 글 작성하기 -- 관리자만 작성할수있음 관리자만 삭제가능

    //C : 게시판 임시 글 작성하기

    //R : 전체 게시판 보기
    @GetMapping("/post")
    public ResponseEntity<List<Post>> showAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    //R : 특정 게시판 보기
    @GetMapping("/post/{postNo}")
    public ResponseEntity <Post> show(@PathVariable int postNo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.show(postNo));
    }

    //R :
    // 특정 멤버의 모든 게시판 조회 memberNo 받아와서 작성하기

    @GetMapping("/post/{id}")
    public ResponseEntity <List<Post>> postList(@PathVariable String id) {
       return ResponseEntity.status(HttpStatus.OK).body(service.findByMemberId(id));
  }

    //U : 내 게시판 수정하기

    /*내 게시판만 수정할수있음 memberNo에 postNo으로 해야해*/
    @PutMapping("/post")
    public ResponseEntity <Post> update(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(post));
    }

    //D : 특정 내 게시판 삭제하기
    /*내 게시판만 지울수있음 memberNo에 postNo으로 해야해*/
    @DeleteMapping("/post/{postNo}")
    public ResponseEntity <Post> postUserDelete(@PathVariable int postNo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(postNo));
    }

    //=====================================댓글==========================================
    // C :댓글 추가
    @PostMapping("/post/pcomment")
    public ResponseEntity<PostComment> create(@RequestBody PostComment postComment){

        Post post = new Post();
        post.setPostNo(1);
        postComment.setPost(post);

        Member member = new Member();
        member.setMemberNo(1);
        postComment.setMember(member);

       return ResponseEntity.status(HttpStatus.OK).body(pCommentService.create(postComment));
    }

    // 댓글 전체 보기
    @GetMapping("/post/{postNo}/pcomment")
    public ResponseEntity<List<PostComment>> postComment(@PathVariable int postNo){

        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.findByPostNo(postNo));

    }
    //U : 내 댓글 수정하기
    @PutMapping("/post/pcomment")
    public ResponseEntity <PostComment> update(@RequestBody PostComment postComment) {
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.update(postComment));
    }


    // D : 댓글 삭제
    @DeleteMapping("/post/pcomment/{id}")
    public ResponseEntity<PostComment> deletePDelete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.delete(id));
    }

    //================================대댓글==========================================

    //C : 대댓글 추가
    @PostMapping("/post/pcomment/incomment")
    public ResponseEntity <InComment> create(@RequestBody InComment incomment) {
        Member member = new Member();
        Post post = new Post();
        PostComment pcomment = new PostComment();
        member.setMemberNo(1);
        post.setPostNo(1);
        pcomment.setCommentNo(3);
        incomment.setMember(member);
        incomment.setPost(post);
        incomment.setPostComment(pcomment);
        return ResponseEntity.status(HttpStatus.OK).body(iCommentService.create(incomment));
    }

    //R : 대댓글 전체 보기
    @GetMapping("/post/{postNo}/pcomment/{commentNo}/incomment")
    public ResponseEntity<List<InComment>> showAll(@PathVariable int postNo, @PathVariable int commentNo) {
        return ResponseEntity.status(HttpStatus.OK).body(iCommentService.showAll( postNo, commentNo));
    }

    //U : 대댓글 수정하기
    @PutMapping("/post/pcomment/incomment")
    public ResponseEntity <InComment> update(@RequestBody InComment incomment) {
        return ResponseEntity.status(HttpStatus.OK).body(iCommentService.update(incomment));
    }

    //D : 대댓글 삭제하기
    @DeleteMapping("/post/pcomment/incomment/{incono}")
    public ResponseEntity <InComment> inCommentDelete(@PathVariable int incono) {
        return ResponseEntity.status(HttpStatus.OK).body(iCommentService.delete(incono));
    }

}
