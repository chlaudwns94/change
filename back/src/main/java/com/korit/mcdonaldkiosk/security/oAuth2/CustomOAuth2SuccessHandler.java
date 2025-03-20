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
    private String protocol;
    @Value(value = "${react.server.host}")
    private String host;
    @Value(value = "${react.server.port}")
    private int port;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        Admin admin = principalUser.getAdmin();
        Date expires = new Date(new Date().getTime() + (1000l * 60 * 60 * 7));
        String accessToken = jwtUtil
                .generateToken(admin.getEmail(), Integer.toString(admin.getAdminId()), expires);
        response.sendRedirect(String.format("%s://%s:%d/admin/login/oauth2?accessToken=%s", protocol, host, port, accessToken));
    }

}
