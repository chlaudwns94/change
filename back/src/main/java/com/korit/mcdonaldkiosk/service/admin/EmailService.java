package com.korit.mcdonaldkiosk.service.admin;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.repository.admin.AdminRepository;
import com.korit.mcdonaldkiosk.security.jwt.JwtUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class EmailService {

    private final String FROM_EMAIL = "chlaudwns94@gmail.com"; // 이메일 발신자 주소

    @Autowired(required = false)
    private JavaMailSender javaMailSender; // 이메일 전송을 위한 JavaMailSender 의존성

    @Autowired
    private JwtUtil jwtUtil; // JWT 토큰 생성 및 검증을 위한 JwtUtil 의존성

    @Autowired
    private AdminRepository adminRepository; // 관리자 정보 조회를 위한 AdminRepository 의존성

    /**
     * 계정 활성화 메일 전송 (이메일 인증)
     * @param to 수신자 이메일
     * @param username 인증을 위한 관리자 이름
     * @throws MessagingException 이메일 전송 중 오류 발생 시
     */
    @Async
    public void sendAuthMail(String to, String username) throws MessagingException {
        // 이메일 인증을 위한 토큰 생성
        String emailToken = jwtUtil.generateToken(null, null, new Date(new Date().getTime() + 1000 * 60 * 5)); // 5분간 유효한 토큰
        String href = "http://localhost:8080/api/auth/email?username=" + username + "&token=" + emailToken;

        final String SUBJECT = "[board_project] 계정 활성화 인증 메일입니다."; // 이메일 제목
        String content = String.format("""
            <html lang="ko">
            <head>
                <meta charset="UTF-8">
            </head>
            <body>
              <div style="display: flex; flex-direction: column; align-items: center;">
                <h1>계정 활성화</h1>
                <p>계정 활성화를 하시려면 아래의 인증 버튼을 클릭하세요.</p>
                <a style="box-sizing: border-box; border: none; border-radius: 8px; padding: 7px 15px; background-color: #2383e2; color: #ffffff; text-decoration: none;" target="_blank" href="%s">인증하기</a>
              </div>
            </body>
            </html>
        """, href);

        sendMail(to, SUBJECT, content); // 이메일 전송
    }

    /**
     * 실제 이메일 전송 메서드
     * @param to 수신자 이메일
     * @param subject 이메일 제목
     * @param content 이메일 내용
     * @throws MessagingException 이메일 전송 중 오류 발생 시
     */
    public void sendMail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // MIME 메시지 생성

        // HTML 형식으로 이메일 내용 설정
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom(FROM_EMAIL); // 발신자 설정
        mimeMessageHelper.setTo(to); // 수신자 설정
        mimeMessageHelper.setSubject(subject); // 이메일 제목 설정
        mimeMessage.setText(content, StandardCharsets.UTF_8.name(), "html"); // HTML 콘텐츠 설정

        javaMailSender.send(mimeMessage); // 이메일 전송
    }

    /**
     * 이메일 인증 토큰을 통해 계정 활성화 처리
     * @param adminName 관리자 이름
     * @param token 이메일 인증 토큰
     * @return 인증 결과 메시지
     */
    @Transactional(rollbackFor = Exception.class)
    public String auth(String adminName, String token) {
        String responseMessage = "";
        try {
            jwtUtil.parseToken(token); // 토큰 검증

            Optional<Admin> adminOptional = adminRepository.findAdminByAdminName(adminName); // 관리자 정보 조회
            if(adminOptional.isEmpty()) {
                responseMessage = "[인증실패] 존재하지 않는 사용자입니다."; // 사용자가 존재하지 않음
            } else {
                Admin admin = adminOptional.get();
                if(admin.getAccountEnabled() == 1) {
                    responseMessage = "[인증실패] 이미 인증된 사용자입니다."; // 이미 인증된 사용자
                } else {
                    adminRepository.updateAccountEnabled(adminName); // 계정 활성화
                    responseMessage = "[인증성공] 인증에 성공하였습니다."; // 인증 성공
                }
            }
        } catch (Exception e) {
            responseMessage = "[인증실패] 토큰이 유효하지 않거나 인증 시간을 초과하였습니다."; // 토큰 오류 처리
        }

        return responseMessage;
    }

    /**
     * 랜덤한 이메일 인증 코드를 생성
     * @return 생성된 인증 코드
     */
    public String generateEmailCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(1000000)); // 6자리 랜덤 코드
    }

    /**
     * 이메일 변경을 위한 인증 메일 전송
     * @param to 수신자 이메일
     * @param code 인증 코드
     * @throws MessagingException 이메일 전송 중 오류 발생 시
     */
    @Async
    public void sendChangeEmailVerification(String to, String code) throws MessagingException {
        final String SUBJECT = "[board_project] 이메일 변경을 위한 사용자 인증 메일입니다."; // 이메일 제목
        String content = String.format("""
            <html lang="ko">
            <head>
                <meta charset="UTF-8">
            </head>
            <body>
              <div style="display: flex; flex-direction: column; align-items: center;">
                <h1>이메일 인증</h1>
                <p>계정의 이메일 정보를 변경하려면 아래의 인증 코드번호를 확인하세요.</p>
                <h3 style="background-color: #2383e2; color: #ffffff; margin: 20px 0;">%s</h3>
              </div>
            </body>
            </html>
        """, code);

        sendMail(to, SUBJECT, content); // 이메일 전송
    }
}
