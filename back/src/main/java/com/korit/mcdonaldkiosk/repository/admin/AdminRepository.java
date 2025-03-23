package com.korit.mcdonaldkiosk.repository.admin;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.entity.OAuth2;
import com.korit.mcdonaldkiosk.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * AdminRepository 클래스
 *
 * 관리자 관련 데이터베이스 작업을 수행하는 리포지토리 클래스
 * MyBatis를 통해 AdminMapper와 연결하여 관리자 정보를 조회, 저장 및 업데이트 작업을 처리
 */
@Repository
public class AdminRepository {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 관리자 이름을 이용하여 관리자를 찾는 메서드
     *
     * @param adminName 관리자 이름
     * @return 관리자 정보를 담고 있는 Optional
     */
    public Optional<Admin> findAdminByAdminName(String adminName) {
        return Optional.ofNullable(adminMapper.selectByAdminName(adminName));
    }

    /**
     * 관리자를 데이터베이스에 저장하는 메서드
     *
     * @param admin 관리자 정보
     * @return 저장된 관리자 정보가 포함된 Optional
     */
    public Optional<Admin> save(Admin admin) {
        try {
            adminMapper.insertAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(admin);
    }

    /**
     * 관리자 ID를 이용하여 관리자를 찾는 메서드
     *
     * @param adminId 관리자 ID
     * @return 관리자 정보를 담고 있는 Optional
     */
    public Optional<Admin> findAdminById(int adminId) {
        return Optional.ofNullable(adminMapper.selectByAdminId(adminId));
    }

    /**
     * OAuth2 정보를 데이터베이스에 저장하는 메서드
     *
     * @param oAuth2 OAuth2 인증 정보
     * @return 저장된 OAuth2 정보가 포함된 Optional
     */
    public Optional<OAuth2> saveOAuth2(OAuth2 oAuth2) {
        try {
            adminMapper.saveOAuth2(oAuth2);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(oAuth2);
    }

    /**
     * 관리자의 비밀번호를 업데이트하는 메서드
     *
     * @param adminID 관리자 ID
     * @param adminPassword 새 비밀번호
     */
    public void updatePassword(int adminID, String adminPassword) {
        adminMapper.updatePasswordById(adminID, adminPassword);
    }

    /**
     * 관리자의 상호명을 업데이트하는 메서드
     *
     * @param adminID 관리자 ID
     * @param tradeName 새로운 상호명
     */
    public void updateTradeName(int adminID, String tradeName) {
        adminMapper.updateTradeNameById(adminID, tradeName);
    }

    /**
     * 관리자의 이메일을 업데이트하는 메서드
     *
     * @param adminID 관리자 ID
     * @param email 새로운 이메일
     */
    public void updateEmail(int adminID, String email) {
        adminMapper.updateEmailById(adminID, email);
    }

    /**
     * 관리자의 계정 활성 상태를 업데이트하는 메서드
     *
     * @param adminName 관리자 이름
     */
    public void updateAccountEnabled(String adminName) {
        adminMapper.updateAccountEnabledByAdminName(adminName);
    }
}
