package com.korit.mcdonaldkiosk.service.admin;

import com.korit.mcdonaldkiosk.dto.request.ReqAdminSignInDto;
import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.repository.admin.AdminSignInRepository;
import com.korit.mcdonaldkiosk.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AdminSignInService {

    @Autowired
    private AdminSignInRepository adminSignInRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String signIn(ReqAdminSignInDto dto) {
        Admin admin = adminSignInRepository
                .findAdminByAdminName(dto.getAdminName())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 확인하세요."));

        if(!passwordEncoder.matches(dto.getAdminPassword(), admin.getAdminPassword())) {
            throw new BadCredentialsException("사용자 정보를 다시 확인하세요.");
        }
        // 이메일 인증 여부 확인
//        if(admin.getAccountEnabled() == 0) {
//            throw new DisabledException("이메일 인증이 필요합니다.");
//        }

        Date expires = new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 7));

        return jwtUtil.generateToken(
                admin.getAdminName(),
                Integer.toString(admin.getAdminId()),
                expires);
    }
}
