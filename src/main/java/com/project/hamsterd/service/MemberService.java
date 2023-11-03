package com.project.hamsterd.service;

//import com.project.hamsterd.SecurityConfig;
import com.project.hamsterd.domain.MemberDTO;
import com.project.hamsterd.domain.StudyGroup;
import com.project.hamsterd.repo.MemberDAO;
import com.project.hamsterd.domain.Member;
import com.project.hamsterd.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemberService {



    @Autowired
    private MemberDAO dao;

//    private SecurityConfig security;


    public List<Member> showAll() {
        return dao.findAll();
    }

    public Member show(String id){
        Member mem = dao.findByMemberId(id);

        return mem;

    }
    public List<Member> getManagerList(){
        return dao.getManagerList();
    }

    public Member show(String id, String password){
        Member mem = dao.findByMemberId(id);

        if(mem.getId().equals(id) && mem.getPassword().equals(password)){
            return mem;
        }

        return null;

    }public Member show(int memberNo){
        Member mem = dao.findById(memberNo).orElse(null);

//        if(mem.getId().equals(id) && mem.getPassword().equals(password)){
//            return mem;
//        }

        return mem;

    }



    public Member showById(String id)
    {

        return dao.findByMemberId(id);

    }

    public Member showMemberbyMemberNO(int memberNo)
    {

        return dao.showMemberbyMemberNO(memberNo);

    }
    public Member findById(String id){
        return dao.findByMemberId(id);
    }


    public Member findByNick(String nick){
        return dao.findByNick(nick);
    }


    public Member create(Member member) {
        return dao.save(member);
    }

    public Member update(Member member) {
        Member target = dao.findByMemberId(member.getId()); // 기존 DB에 있는 데이터를 member의 아이디로 검색

        if (target != null) {
            target.setPassword(member.getPassword());
            target.setNickname(member.getNickname());
            target.setProfile(member.getProfile());

            return dao.save(target);
        }
        return null;
    }

    public Member delete(int id) {
        Member target = dao.findById(id).orElse(null);
        dao.delete(target);
        return target;
    }

    public List<Member> inGroup(int groupNo){

        List<Member> target = dao.findByGroupNo(groupNo);

        return target;
    }

    public Member findManager(int groupNo){

        Member target = dao.findManager(groupNo);
//        System.out.println(target);

        return target;
    }
    public Member getByCredentials(String id, String password, PasswordEncoder encoder){
        Member member = dao.findByMemberId(id);

        if(member != null && encoder.matches(password, member.getPassword())){
            return member;
        }

        return null;
    }

    public MemberDTO makeToken(Member member, TokenProvider tokenProvider){

        String token = tokenProvider.create(member);
        MemberDTO responseDTO = MemberDTO.builder()
                                        .memberNo(member.getMemberNo())
                                        .studyGroup(member.getStudyGroup())
                                        .id(member.getId())
                                        .name(member.getName())
                                        .nickname(member.getNickname())
                                        .authority(member.getAuthority())
                                        .profile(member.getProfile())
                                        .email(member.getEmail())
                                        .phone(member.getPhone())
                                        .birth(member.getBirth())
                                        .gender(member.getGender())
                                        .address(member.getAddress())
                                        .studyGroup(member.getStudyGroup())
                                        .token(token)
                                        .build();


        System.out.println("생성된 DTO : " + responseDTO);

        return responseDTO;

    }

}