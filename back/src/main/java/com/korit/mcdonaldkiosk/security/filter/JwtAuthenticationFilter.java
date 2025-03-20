package com.korit.mcdonaldkiosk.security.filter;


import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.repository.admin.AdminRepository;
import com.korit.mcdonaldkiosk.security.jwt.JwtUtil;
import com.korit.mcdonaldkiosk.security.pricipal.PrincipalUser;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        jwtAuthentication(getAccessToken(request));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void jwtAuthentication(String accessToken) {
        if(accessToken == null) {return;}
        Claims claims = jwtUtil.parseToken(accessToken);

        int adminId = Integer.parseInt(claims.getId());
        Admin admin = adminRepository.findAdminById(adminId).get();

        PrincipalUser principalUser = PrincipalUser.builder().admin(admin).build();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getAccessToken(HttpServletRequest request) {
        String accessToken = null;
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            accessToken = authorization.substring(7);
        }

        return accessToken;
    }

}