package com.korit.mcdonaldkiosk.service.admin;

import com.korit.mcdonaldkiosk.dto.request.ReqAdminSignUpDto;
import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.repository.admin.AdminSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdminSignUpService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AdminSignUpRepository adminSignUpRepository;


    @Transactional(rollbackFor = Exception.class)
    public Admin signUp(ReqAdminSignUpDto reqAdminSignUpDto) {

        Admin admin = Admin.builder()
                .adminName(reqAdminSignUpDto.getAdminName())
                .adminPassword(passwordEncoder.encode(reqAdminSignUpDto.getAdminPassword()))
                .email(reqAdminSignUpDto.getEmail())
                .tradeName(reqAdminSignUpDto.getTradeName())
                .build();

                adminSignUpRepository.save(admin);
                return admin;
    }

}