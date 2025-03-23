package com.korit.mcdonaldkiosk.security.oAuth2;

import com.korit.mcdonaldkiosk.entity.OAuth2;
import com.korit.mcdonaldkiosk.security.pricipal.PrincipalUser;
import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.security.jwt.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value(value = "${react.server.protocol}")
    private String protocol;  // React 서버의 프로토콜 (http 또는 https)

    @Value(value = "${react.server.host}")
    private String host;  // React 서버의 호스트 (예: localhost)

    @Value(value = "${react.server.port}")
    private int port;  // React 서버의 포트 (예: 3000)

    @Autowired
    private JwtUtil jwtUtil;  // JWT 생성에 사용되는 유틸리티 클래스

    /**
     * OAuth2 인증 성공 후 호출되는 메서드
     *
     * @param request HTTP 요청
     * @param response HTTP 응답
     * @param authentication 인증된 사용자 정보
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 인증된 사용자의 정보를 가져옵니다.
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        Admin admin = principalUser.getAdmin();

        // JWT 토큰의 만료 시간을 설정 (현재 시간에서 7시간 후)
        Date expires = new Date(new Date().getTime() + (1000L * 60 * 60 * 7));

        // JWT 토큰을 생성합니다.
        String accessToken = jwtUtil
                .generateToken(admin.getEmail(), Integer.toString(admin.getAdminId()), expires);

        // 성공적으로 인증이 완료되면 React 클라이언트로 리디렉션하여 JWT 토큰을 전달합니다.
        response.sendRedirect(String.format("%s://%s:%d/admin/login/oauth2?accessToken=%s", protocol, host, port, accessToken));
    }
}
