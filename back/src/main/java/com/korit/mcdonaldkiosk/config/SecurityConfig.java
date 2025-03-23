package com.korit.mcdonaldkiosk.config;

import com.korit.mcdonaldkiosk.security.filter.JwtAuthenticationFilter;
import com.korit.mcdonaldkiosk.security.handler.CustomAuthenticationEntryPoint;
import com.korit.mcdonaldkiosk.security.oAuth2.CustomOAuth2SuccessHandler;
import com.korit.mcdonaldkiosk.security.oAuth2.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter; // JWT 인증 필터
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService; // OAuth2 사용자 서비스
    @Autowired
    private CustomOAuth2SuccessHandler customOAuth2SuccessHandler; // OAuth2 인증 성공 핸들러
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint; // 인증 예외 처리 핸들러

    /**
     * 비밀번호 암호화를 위한 BCryptPasswordEncoder 빈 등록
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Spring Security 설정을 구성하는 메서드
     * @param http HttpSecurity 객체
     * @return SecurityFilterChain 보안 필터 체인
     * @throws Exception 예외 발생 시 처리
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CORS 설정을 기본값으로 적용
        http.cors(Customizer.withDefaults());

        // CSRF 보호 기능 비활성화
        http.csrf(csrf -> csrf.disable());

        // 세션을 상태 비저장 방식(STATELESS)으로 설정
        http.sessionManagement(sessionManagement -> {
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        // OAuth2 로그인 설정
        http.oauth2Login(oauth2 -> {
            oauth2.userInfoEndpoint(userInfoEndpoint -> {
                userInfoEndpoint.userService(customOAuth2UserService); // 사용자 정보 서비스 설정
            });
            oauth2.successHandler(customOAuth2SuccessHandler); // OAuth2 로그인 성공 핸들러 설정
        });

        // JWT 인증 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 인증 예외 처리 설정
        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(customAuthenticationEntryPoint);
        });

        // 요청별 접근 제어 설정
        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests.requestMatchers(
                    "/api/auth/**",
                    "/api/user/**",
                    "/image/**",
                    "/admin/**",
                    "/swagger-ui/**",
                    "/swagger-resources/**",
                    "/order-controller",
                    "/v3/api-docs/**",
                    "/user/**"
            ).permitAll(); // 특정 엔드포인트는 인증 없이 접근 허용
            authorizeRequests.anyRequest().authenticated(); // 그 외 모든 요청은 인증 필요
        });

        return http.build();
    }

    /**
     * CORS 설정을 구성하는 메서드
     * @return CorsConfigurationSource CORS 설정 정보
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // 모든 도메인 허용
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
