package com.korit.mcdonaldkiosk.controller.user;

import com.korit.mcdonaldkiosk.dto.request.ReqPointDto;
import com.korit.mcdonaldkiosk.service.user.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/processPoint")
    public ResponseEntity<String> processPoint(@RequestBody ReqPointDto reqPointDto) {
        try {
            System.out.println("Received Request: " + reqPointDto); // 요청 데이터 로그 출력

            // 포인트 적립 또는 차감 처리
            pointService.processPoint(reqPointDto, reqPointDto.getCalcul());

            // 처리 성공 시
            return ResponseEntity.ok("포인트 처리 성공");
        } catch (IllegalArgumentException e) {
            // 잘못된 계산 값이 전달되었을 경우
            return ResponseEntity.badRequest().body("Invalid calcul value: " + reqPointDto.getCalcul());
        }
    }
}