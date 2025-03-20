package com.korit.mcdonaldkiosk.controller.advice;

import com.korit.mcdonaldkiosk.exception.DuplicatedValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(DuplicatedValueException.class)
    public ResponseEntity<?> duplicatedException(DuplicatedValueException e) {
        // 중복된 값이 있을 때의 오류 메시지
        return ResponseEntity.badRequest().body("중복된 값이 발견되었습니다: " + e.getFieldErrors());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException e) {
        // 사용자 이름을 찾을 수 없을 때의 오류 메시지
        return ResponseEntity.badRequest().body("사용자 이름을 찾을 수 없습니다: " + e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException(BadCredentialsException e) {
        // 인증 정보가 잘못되었을 때의 오류 메시지
        return ResponseEntity.badRequest().body("잘못된 인증 정보입니다: " + e.getMessage());
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<?> disabledException(DisabledException e) {
        // 계정이 비활성화 되었을 때의 오류 메시지
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("계정이 비활성화되었습니다: " + e.getMessage());
    }

}

