package com.korit.mcdonaldkiosk.service.admin;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.repository.admin.AdminRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;  // AdminRepository를 통해 DB와 상호작용
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  // 비밀번호 암호화를 위한 클래스

    // 관리자 비밀번호 업데이트 메서드
    @Transactional(rollbackFor = Exception.class)  // 트랜잭션을 처리하고, 예외 발생 시 롤백
    public void updatePassword(Admin admin, String password) {
        String encodedPassword = passwordEncoder.encode(password);  // 비밀번호 암호화
        adminRepository.updatePassword(admin.getAdminId(), encodedPassword);  // 암호화된 비밀번호 DB에 저장
    }

    // 관리자 닉네임(상호명) 업데이트 메서드
    @Transactional(rollbackFor = Exception.class)  // 트랜잭션 처리
    public void updateNickname(Admin admin, String tradeName) {
        adminRepository.updateTradeName(admin.getAdminId(), tradeName);  // 상호명을 DB에 저장
    }

    // 관리자 이메일 업데이트 메서드
    @Transactional(rollbackFor = Exception.class)  // 트랜잭션 처리
    public void updateEmail(Admin admin, String email) {
        adminRepository.updateEmail(admin.getAdminId(), email);  // 이메일 주소 DB에 저장
    }

    // 관리자 이름으로 관리자 정보 조회 메서드
    public Admin getUserByUsername(String adminName) throws Exception {
        return adminRepository
                .findAdminByAdminName(adminName)  // 관리자 이름을 통해 관리자를 DB에서 조회
                .orElseThrow(() -> new NotFoundException("사용자를 찾지 못했습니다."));  // 관리자가 없으면 예외 발생
    }
}
