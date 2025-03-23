package com.korit.mcdonaldkiosk.security.filter;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.repository.admin.AdminRepository;
import com.korit.mcdonaldkiosk.security.jwt.JwtUtil;
import com.korit.mcdonaldkiosk.security.pricipal.PrincipalUser;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JwtAuthenticationFilter 클래스
 *
 * HTTP 요청에 포함된 JWT 토큰을 확인하고, 해당 토큰을 기반으로 인증을 처리하는 필터 클래스.
 * JWT 토큰에서 사용자의 정보를 추출하여 SecurityContext에 인증 정보를 설정.
 */
@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminRepository adminRepository;

    /**
     * 요청에 포함된 JWT 토큰을 확인하고 인증을 처리하는 메서드
     *
     * @param servletRequest  HTTP 요청
     * @param servletResponse HTTP 응답
     * @param filterChain     필터 체인
     * @throws IOException      입출력 예외
     * @throws ServletException 서블릿 예외
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 토큰을 사용하여 인증 수행
        jwtAuthentication(getAccessToken(request));

        // 다음 필터로 요청 전달
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * JWT 토큰을 사용하여 인증을 처리하는 메서드
     *
     * @param accessToken JWT 토큰
     */
    private void jwtAuthentication(String accessToken) {
        if (accessToken == null) {
            return;
        }

        // JWT 토큰에서 사용자 정보를 추출
        Claims claims = jwtUtil.parseToken(accessToken);

        // 관리자 정보 조회
        int adminId = Integer.parseInt(claims.getId());
        Admin admin = adminRepository.findAdminById(adminId).get();

        // 인증된 사용자 객체 생성
        PrincipalUser principalUser = PrincipalUser.builder().admin(admin).build();

        // 인증 객체를 SecurityContext에 설정
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * HTTP 요청에서 JWT 토큰을 추출하는 메서드
     *
     * @param request HTTP 요청
     * @return JWT 토큰
     */
    private String getAccessToken(HttpServletRequest request) {
        String accessToken = null;
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더에서 'Bearer '로 시작하는 토큰을 추출
        if (authorization != null && authorization.startsWith("Bearer ")) {
            accessToken = authorization.substring(7);
        }

        return accessToken;
    }

}
