package com.korit.mcdonaldkiosk.repository.admin;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.entity.OAuth2;
import com.korit.mcdonaldkiosk.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdminRepository {


    @Autowired
    private AdminMapper adminMapper;

    public Optional<Admin> findAdminByAdminName(String adminName) {
        return Optional.ofNullable(adminMapper.selectByAdminName(adminName));
    }

    public Optional<Admin> save(Admin admin) {
        try {
            adminMapper.insertAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(admin);
    }

    public Optional<Admin> findAdminById(int adminId) {
        return Optional.ofNullable(adminMapper.selectByAdminId(adminId));
    }

    public Optional<OAuth2> saveOAuth2(OAuth2 oAuth2) {
        try {
            adminMapper.saveOAuth2(oAuth2);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(oAuth2);
    }

    public void updatePassword(int adminID, String adminPassword) {
        adminMapper.updatePasswordById(adminID, adminPassword);
    }

    public void updateTradeName(int adminID, String tradeName) {
        adminMapper.updateTradeNameById(adminID, tradeName);
    }
    public void updateEmail(int adminID, String email) {
        adminMapper.updateEmailById(adminID, email);
    }
    public void updateAccountEnabled(String adminName) {
        adminMapper.updateAccountEnabledByAdminName(adminName);
    }

}
