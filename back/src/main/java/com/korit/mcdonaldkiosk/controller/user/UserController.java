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

/**
 * 사용자 관련 정보를 처리하는 컨트롤러
 *
 * - 사용자의 회원 가입, 사용자 검색 등의 기능을 제공하는 API를 구현한다.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 사용자 정보 저장 API
     *
     * @param reqUserDto 사용자 정보를 담은 DTO
     * @return 사용자 저장 결과
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody ReqUserDto reqUserDto) {
        return ResponseEntity.ok().body(userService.save(reqUserDto));
    }

    /**
     * 사용자 검색 API
     *
     * @param reqUserFindDto 검색할 사용자 정보를 담은 DTO
     * @return 검색된 사용자 정보
     */
    @PostMapping("/find")
    public ResponseEntity<?> findUser(@RequestBody ReqUserFindDto reqUserFindDto) {
        return ResponseEntity.ok().body(userService.findUser(reqUserFindDto));
    }
}
