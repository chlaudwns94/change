package com.korit.mcdonaldkiosk.controller.admin;

import com.korit.mcdonaldkiosk.dto.request.ReqAdminSignUpDto;
import com.korit.mcdonaldkiosk.service.admin.AdminSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AdminSignUpController {

    @Autowired
    private AdminSignUpService adminSignUpService;

    @PostMapping("/join")
    public ResponseEntity<?> joinIn(@RequestBody ReqAdminSignUpDto reqAdminSignUpDto) {
        return ResponseEntity.ok().body(adminSignUpService.signUp(reqAdminSignUpDto));

    }

}
