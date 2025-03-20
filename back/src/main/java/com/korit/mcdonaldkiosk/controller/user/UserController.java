package com.korit.mcdonaldkiosk.controller.user;

import com.korit.mcdonaldkiosk.dto.request.ReqUserDto;
import com.korit.mcdonaldkiosk.dto.request.ReqUserFindDto;
import com.korit.mcdonaldkiosk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody ReqUserDto reqUserDto) {
        return ResponseEntity.ok().body(userService.save(reqUserDto));
    }

    @PostMapping("/find")
    public ResponseEntity<?> findUser(@RequestBody ReqUserFindDto reqUserFindDto) {
        return ResponseEntity.ok().body(userService.findUser(reqUserFindDto));
    }
}
