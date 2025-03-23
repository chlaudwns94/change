package com.korit.mcdonaldkiosk.controller.admin;

import com.korit.mcdonaldkiosk.dto.request.ReqAuthEmailDto;
import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.service.admin.AdminService;
import com.korit.mcdonaldkiosk.service.admin.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 인증 이메일을 처리하는 컨트롤러
 *
 * - 관리자 계정의 이메일 인증을 위한 API를 제공한다.
 */
@RestController
public class AuthController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminService adminService;

    /**
     * 인증 이메일 발송 API
     *
     * @param dto 인증 요청 데이터 (관리자 이름)
     * @return 이메일 전송 결과 응답
     * @throws Exception 이메일 전송 과정에서 발생하는 예외
     */
    @PostMapping("/email")
    public ResponseEntity<?> sendAuthEmail(@RequestBody ReqAuthEmailDto dto) throws Exception {
        Admin admin = adminService.getUserByUsername(dto.getAdminName());
        emailService.sendAuthMail(admin.getEmail(), dto.getAdminName());
        return ResponseEntity.ok().build();
    }

    /**
     * 이메일 인증 확인 API
     *
     * @param username 인증할 관리자 이름
     * @param token    인증 토큰
     * @return 인증 결과를 포함한 HTML 응답 (알림 메시지 및 창 닫기)
     */
    @GetMapping("/email")
    public ResponseEntity<?> setAuthMail(
            @RequestParam String username,
            @RequestParam String token
    ) {
        String script = String.format("""
            <script>
                alert("%s");
                window.close();
            </script>    
        """, emailService.auth(username, token));

        return ResponseEntity.ok().header("Content-Type", "text/html; charset=utf-8").body(script);
    }
}
