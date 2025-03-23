package com.korit.mcdonaldkiosk.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JwtUtil 클래스
 *
 * JWT 토큰을 생성하고 파싱하는 유틸리티 클래스입니다.
 * JWT를 사용하여 인증 정보를 관리하고, 토큰의 서명 및 유효성 검사를 담당합니다.
 */
@Component
public class JwtUtil {

    private Key key;

    /**
     * JwtUtil 생성자
     *
     * @param secret JWT 서명에 사용할 비밀 키 (Base64로 인코딩된 문자열)
     */
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // Base64로 디코딩된 비밀 키를 사용하여 서명에 필요한 키 생성
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    /**
     * JWT 토큰을 생성하는 메서드
     *
     * @param subject  토큰의 주체 (보통 사용자 ID)
     * @param id       토큰의 고유 ID
     * @param expires  토큰의 만료 일시
     * @return 생성된 JWT 토큰
     */
    public String generateToken(String subject, String id, Date expires) {
        return Jwts.builder()
                .setSubject(subject)  // 주체 설정
                .setId(id)            // 토큰 고유 ID 설정
                .setExpiration(expires)  // 만료 일시 설정
                .signWith(key, SignatureAlgorithm.HS256)  // 서명 알고리즘 및 키 설정
                .compact();  // 토큰 생성
    }

    /**
     * JWT 토큰을 파싱하여 클레임(Claims)을 반환하는 메서드
     *
     * @param token JWT 토큰
     * @return JWT의 클레임 정보 (토큰에서 추출된 정보)
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)  // 서명 키 설정
                .parseClaimsJws(token)  // 토큰을 파싱하여 클레임 반환
                .getBody();
    }
}
