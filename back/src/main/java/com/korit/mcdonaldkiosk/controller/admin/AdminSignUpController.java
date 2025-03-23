package com.korit.mcdonaldkiosk.controller.admin;

import com.korit.mcdonaldkiosk.dto.request.ReqAdminSignUpDto;
import com.korit.mcdonaldkiosk.service.admin.AdminSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 관리자 회원가입을 처리하는 컨트롤러
 *
 * - 관리자 계정을 생성하는 API를 제공한다.
 */
@RestController
@RequestMapping("/api/auth")
public class AdminSignUpController {

    @Autowired
    private AdminSignUpService adminSignUpService;

    /**
     * 관리자 회원가입 API
     *
     * @param reqAdminSignUpDto 회원가입 요청 데이터 (이메일, 비밀번호, 닉네임 등)
     * @return 회원가입 결과 (성공 시 가입된 관리자 정보 반환)
     */
    @PostMapping("/join")
    public ResponseEntity<?> joinIn(@RequestBody ReqAdminSignUpDto reqAdminSignUpDto) {
        return ResponseEntity.ok().body(adminSignUpService.signUp(reqAdminSignUpDto));
    }

}
