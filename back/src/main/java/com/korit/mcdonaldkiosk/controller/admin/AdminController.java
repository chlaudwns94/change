package com.korit.mcdonaldkiosk.controller.admin;

import com.korit.mcdonaldkiosk.security.pricipal.PrincipalUser;
import com.korit.mcdonaldkiosk.service.admin.AdminService;
import com.korit.mcdonaldkiosk.service.admin.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService; // 관리자 계정 관련 서비스

    @Autowired
    private EmailService emailService; // 이메일 서비스

    /**
     * 현재 로그인한 관리자 정보 조회
     *
     * @param principalUser 현재 인증된 관리자 정보
     * @return 관리자 정보 반환
     */
    @GetMapping("/user/me")
    public ResponseEntity<?> getLoginUser(@AuthenticationPrincipal PrincipalUser principalUser) {
        System.out.println(principalUser.getAdmin());
        return ResponseEntity.ok().body(principalUser.getAdmin());
    }

    /**
     * 관리자 비밀번호 변경
     *
     * @param principalUser 현재 인증된 관리자 정보
     * @param requestBody 요청 본문 (새로운 비밀번호 포함)
     * @return HTTP 상태 200 OK 반환
     */
    @PutMapping("/user/profile/password")
    public ResponseEntity<?> changePassword(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody
    ) {
        String password = requestBody.get("password");
        adminService.updatePassword(principalUser.getAdmin(), password);
        return ResponseEntity.ok().build();
    }

    /**
     * 관리자 닉네임 변경
     *
     * @param principalUser 현재 인증된 관리자 정보
     * @param requestBody 요청 본문 (새로운 닉네임 포함)
     * @return HTTP 상태 200 OK 반환
     */
    @PutMapping("/user/profile/nickname")
    public ResponseEntity<?> changeNickname(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody
    ) {
        String nickname = requestBody.get("nickname");
        adminService.updateNickname(principalUser.getAdmin(), nickname);
        return ResponseEntity.ok().build();
    }

    /**
     * 이메일 변경을 위한 인증 코드 발송
     *
     * @param requestBody 요청 본문 (새로운 이메일 포함)
     * @return 생성된 인증 코드 반환
     * @throws MessagingException 이메일 전송 중 오류 발생 시 예외 처리
     */
    @PostMapping("/user/profile/email/send")
    public ResponseEntity<?> sendEmailChangeVerification(
            @RequestBody Map<String, String> requestBody
    ) throws MessagingException {
        String email = requestBody.get("email");
        String code = emailService.generateEmailCode();
        emailService.sendChangeEmailVerification(email, code);
        return ResponseEntity.ok().body(code);
    }

    /**
     * 관리자 이메일 변경
     *
     * @param principalUser 현재 인증된 관리자 정보
     * @param requestBody 요청 본문 (새로운 이메일 포함)
     * @return HTTP 상태 200 OK 반환
     */
    @PutMapping("/user/profile/email")
    public ResponseEntity<?> changeEmail(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody
    ) {
        String email = requestBody.get("email");
        adminService.updateEmail(principalUser.getAdmin(), email);
        return ResponseEntity.ok().build();
    }
}
