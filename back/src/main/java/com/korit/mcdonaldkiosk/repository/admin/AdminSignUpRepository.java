package com.korit.mcdonaldkiosk.repository.admin;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdminSignUpRepository {

    @Autowired
    private AdminMapper adminMapper;

    public Optional<Admin> save(Admin admin) {
        try {
            adminMapper.insertAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(admin);
    }

}
