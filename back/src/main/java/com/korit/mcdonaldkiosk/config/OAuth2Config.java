package com.korit.mcdonaldkiosk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

@Configuration
public class OAuth2Config {


    /**
     * OAuth2 인증 과정에서 사용자 정보를 가져오는 서비스 객체를 빈으로 등록합니다.
     * Spring Security의 OAuth2 로그인 기능에서 사용자 정보를 조회하고, 가공하는 역할을 합니다.
     *
     * @return DefaultOAuth2UserService OAuth2 사용자 정보를 처리하는 기본 서비스 객체
     */
    @Bean
    public DefaultOAuth2UserService defaultOAuth2UserService() {
        return new DefaultOAuth2UserService();
    }
}
