package com.project.hamsterd.security;


import com.project.hamsterd.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@Slf4j
public class TokenProvider {
    private static final String SECRET_KEY="FlRpX30MqDbiAkmlfArbrmVkDD4RqISskGZmBFax5oGVxzXXWUzTR5JyskiHMIV9M10icegkpi46AdvrcX1E6CmTUBc6IFbTPiD";

    public String create(Member member){

//        // claim(데이터)를 담는 객체 정의
//        Claims claims = Jwts.claims();
//
//        // payload에 담을 claim들 추가
//        claims.put("memberNo", member.getMemberNo());
//        claims.put("id", member.getId());
//        claims.put("name", member.getName());
//        claims.put("nickName", member.getNickname());
//        claims.put("authority", member.getAuthority());
//        claims.put("birth", member.getBirth());
//        claims.put("address", member.getAddress());
//        claims.put("gender", member.getGender());
//        claims.put("academy", member.getAcademy());
//        claims.put("phone", member.getPhone());
//        claims.put("profile", member.getProfile());
//        claims.put("weight", member.getWeight());
//        claims.put("studygroup", member.getStudyGroup());


        // 토근 생성 -> 기한 지정 가능
        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS)) ;
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)  // header에 들어갈 내용
                .setSubject(member.getId())                      // 여기부터 payload에 들어갈 내용
                .setIssuer("hamsterd")                        // 프로젝트 이름
//                .setClaims(claims)                              // claims 담긴 claims 추가npm
                .setIssuedAt(new Date())                         // 현재 날짜
                .setExpiration(expireDate)                       // 언제까지
                .compact();
    }

    public String validateAndGetUserId(String token){// 토큰 검증 메소드, 토큰을 디코딩 및 파싱과 위조여부 확인

        log.info(token);

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
//        return claims.getSubject(); // id를 반환
    }
}