package com.project.hamsterd.controller;

import com.project.hamsterd.domain.*;
import com.project.hamsterd.security.TokenProvider;
import com.project.hamsterd.service.InCommentService;
import com.project.hamsterd.service.MemberService;
import com.project.hamsterd.service.PostCommentService;
import com.project.hamsterd.service.PostService;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.log4j.Log4j2;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@Log4j2
@RequestMapping("/hamsterd/*")
//CORS 요청을 허용할 원본(도메인)을 지정합니다. {"*"}은 모든 원본을 허용
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class PostController {
    @Autowired
    private PostService service;

    @Autowired
    private InCommentService iCommentService;

    @Autowired
    private PostCommentService pCommentService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MemberService memberService;

//    @Value("${file.upload.path}")
//    private String uploadPath;
//
//    @Value("${spring.servlet.multipart.location}")
//    private String uploadPathImage;


    //C : 게시판 작성하기
    @PostMapping("/post")
    public ResponseEntity<Post> create(@RequestParam("title") String title,
                                       @RequestParam("desc") String desc,
                                       @RequestParam("securityCheck") char securityCheck,
                                       @RequestParam("token") String token) {

        log.info("게시판 작성 토큰 " + token);

        String id = tokenProvider.validateAndGetUserId(token);
        log.info("id", id);
        Member member =  memberService.showById(id);
        log.info("게시판 작성 멤버 정보 " + member.toString());
        Post vo = Post.builder()
                .postTitle(title)
                .postContent(desc)
                .securityCheck(securityCheck)
                .member(member)
                .build();
         log.info(vo);

        return ResponseEntity.status(HttpStatus.OK).body(service.create(vo));
    }



    //게시판 조회수 updateBoardView
    @PutMapping("/post/boardView/{postNo}")
    public ResponseEntity<Post> update(@PathVariable int postNo) {
        Post post =new Post();
        post.setPostNo(1);
        log.info("게시판 조회수 들어움");
        return ResponseEntity.status(HttpStatus.OK).body(service.updateBoardView(postNo));
    }

    //C : 관리자 공지 글 작성하기 -- 관리자만 작성할수있음 관리자만 삭제가능

    //C : 게시판 임시 글 작성하기

    //R : 게시판 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<Map<String, Object>> showAll(@RequestParam(name="page", defaultValue = "1") int page) {

        Sort sort = Sort.by("postNo").descending();
        Pageable pageable = PageRequest.of(page-1, 5, sort);
        Page<Post> result = service.showAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("contents", result.getContent());
        response.put("total", result.getTotalElements());

//        log.info("Total Pages : " + result.getTotalPages()); // 총 몇 페이지
//        log.info("Total Count : " + result.getTotalElements()); // 전체 개수
//        log.info("Page Number : " + result.getNumber()); // 현재 페이지 번호
//        log.info("Page Size : " + result.getSize()); // 페이지당 데이터 개수
//        log.info("Next Page : " + result.hasNext()); // 다음 페이지가 있는지 존재 여부
//        log.info("First Page : " + result.isFirst()); // 시작 페이지 여부


        return ResponseEntity.status(HttpStatus.OK).body(response);
//        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    //R :  게시판 번호별 보기
    @GetMapping("/post/detail/{postNo}")
    public ResponseEntity <Post> show(@PathVariable int postNo) {
        log.info("상세페이지 들어옴");
        return ResponseEntity.status(HttpStatus.OK).body(service.show(postNo));
    }

    @GetMapping("/post/search/postContents/{postContent}")
    //R : 검색창에 작성된 게시물만 보기
    public ResponseEntity <List<Post>> findSearchContent(@PathVariable String postContent) {
        log.info("검색창에 작성된 게시물 들어옴" + postContent);

        return ResponseEntity.status(HttpStatus.OK).body(service.findSearchContent(postContent));
    }

    //R : 검색창에 작성된 게시물만 보기
    @GetMapping("/post/search/postTitles/{postTitle}")
    public ResponseEntity <List<Post>> findSearchTitle(@PathVariable String postTitle) {
        log.info("검색창에 작성된 제목 들어옴" + postTitle);
        return ResponseEntity.status(HttpStatus.OK).body(service.findSearchTitle(postTitle));
    }


    //R :
    // 특정 멤버의 모든 게시판 조회 memberNo 받아와서 작성하기

    @GetMapping("/post/{id}")
    public ResponseEntity <List<Post>> postList(@PathVariable int id) {
       return ResponseEntity.status(HttpStatus.OK).body(service.findByMemberId(id));
  }

    //U : 내 게시판 수정하기

    /*내 게시판만 수정할수있음 memberNo에 postNo으로 해야해*/
    @PutMapping("/updatePost")
    public ResponseEntity <Post> update(@RequestParam("postNo") int postNo, @RequestParam("title") String title, @RequestParam("desc") String desc) {

        Post post = new Post();
        post.setPostNo(postNo);
        post.setPostTitle(title);
        log.info(title);
        post.setPostContent(desc);
        log.info(desc);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(post));
    }

    //D : 특정 내 게시판 삭제하기
    /*내 게시판만 지울수있음 memberNo에 postNo으로 해야해*/
    @DeleteMapping("/deletePost/{postNo}")
    public ResponseEntity <Post> delete(@PathVariable int postNo) {
        log.info("포스트삭제 넘버 :" + postNo);
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(postNo));
    }

    //=====================================댓글==========================================
    // C :댓글 추가
    @PostMapping("/post/pcomment")
//    public ResponseEntity<PostComment> create(@RequestParam("comments") String comments){
    public ResponseEntity<PostComment> create(@RequestBody PostComment pComment){


       return ResponseEntity.status(HttpStatus.OK).body(pCommentService.create(pComment));
    }

    // 댓글 전체 보기
    @GetMapping("/post/{postNo}/pcomment")
    public ResponseEntity<List<PostComment>> postComment(@PathVariable int postNo){
        log.info("댓글 전체 보여짐 ? ");
        log.info(pCommentService.showAll());
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.findByPostNo(postNo));

    }
    //U : 내 댓글 수정하기
    @PutMapping("/post/pcomment")
    public ResponseEntity <PostComment> update(@RequestBody PostComment postComment) {
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.update(postComment));
    }


    // D : 댓글 삭제
    @DeleteMapping("/post/pcomment/{commentNo}")
    public ResponseEntity<PostComment> deletePDelete(@PathVariable int commentNo){
        return ResponseEntity.status(HttpStatus.OK).body(pCommentService.delete(commentNo));
    }

    //================================대댓글==========================================

    //C : 대댓글 추가
    @PostMapping("/post/pcomment/incomment")
    public ResponseEntity <InComment> create(@RequestBody InComment incomment) {

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
