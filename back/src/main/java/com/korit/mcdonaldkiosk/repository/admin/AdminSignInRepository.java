package com.korit.mcdonaldkiosk.repository.admin;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdminSignInRepository {

    @Autowired
    private AdminMapper adminMapper;

    public Optional<Admin> findAdminByAdminName(String adminName) {
        return Optional.ofNullable(adminMapper.selectByAdminName(adminName));
    }
}
