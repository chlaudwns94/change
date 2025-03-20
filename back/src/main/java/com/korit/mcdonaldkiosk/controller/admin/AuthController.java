package com.korit.mcdonaldkiosk.controller.admin;

import com.korit.mcdonaldkiosk.dto.request.ReqAuthEmailDto;
import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.service.admin.AdminService;
import com.korit.mcdonaldkiosk.service.admin.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/email")
    public ResponseEntity<?> sendAuthEmail(@RequestBody ReqAuthEmailDto dto) throws Exception {
        Admin admin = adminService.getUserByUsername(dto.getAdminName());
        emailService.sendAuthMail(admin.getEmail(), dto.getAdminName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/email")
    public ResponseEntity<?> setAuthMail(
            @RequestParam String username,
            @RequestParam String token
    ) {

        String script = String.format("""
            <script>
                alert("%s");
                window.close();
            </script>    
        """, emailService.auth(username, token));

        return ResponseEntity.ok().header("Content-Type", "text/html; charset=utf-8").body(script);
    }
}
