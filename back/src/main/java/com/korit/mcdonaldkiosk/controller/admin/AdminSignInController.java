package com.korit.mcdonaldkiosk.controller.admin;

import com.korit.mcdonaldkiosk.dto.request.ReqAdminSignInDto;
import com.korit.mcdonaldkiosk.dto.response.RespTokenDto;
import com.korit.mcdonaldkiosk.service.admin.AdminSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 관리자 로그인 처리를 담당하는 컨트롤러
 *
 * - 관리자의 로그인 요청을 처리하고 JWT 토큰을 발급한다.
 */
@RestController
@RequestMapping("/api/auth")
public class AdminSignInController {

    @Autowired
    private AdminSignInService adminSignInService;

    /**
     * 관리자 로그인 API
     *
     * @param dto 로그인 요청 데이터 (이메일, 비밀번호)
     * @return JWT 토큰 정보 (토큰 유형, 이름, 토큰 값)
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ReqAdminSignInDto dto) {

        RespTokenDto respTokenDto = RespTokenDto.builder()
                .type("JWT") // 토큰 유형 설정
                .name("AccessToken") // 토큰 명칭
                .token(adminSignInService.signIn(dto)) // 로그인 처리 후 토큰 반환
                .build();
        return ResponseEntity.ok().body(respTokenDto);
    }

}
