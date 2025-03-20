package com.korit.mcdonaldkiosk.service.user;

import com.korit.mcdonaldkiosk.dto.request.ReqPointDto;
import com.korit.mcdonaldkiosk.repository.user.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    // 포인트 적립 및 차감 처리
    @Transactional(rollbackFor = Exception.class)
    public void processPoint(ReqPointDto reqPointDto, int calcul) {
        System.out.println("Processing calcul value: " + calcul);
        System.out.println("Received phoneNumber: " + reqPointDto.getPhoneNumber());

        if (calcul != 0 && calcul != 1) {
            throw new IllegalArgumentException("Invalid calcul value: " + calcul);
        }

        Integer userId = pointRepository.findUserIdByPhoneNumber(reqPointDto.getPhoneNumber());
        System.out.println("Retrieved userId: " + userId);

        // 유저가 없다면 새로 생성
        if (userId == null || userId == 0) {
            System.out.println("User not found, creating new user...");
            pointRepository.save(reqPointDto.getPhoneNumber());
            userId = pointRepository.findUserIdByPhoneNumber(reqPointDto.getPhoneNumber());

            if (userId == null || userId == 0) {
                throw new IllegalArgumentException("Failed to create user for phone number: " + reqPointDto.getPhoneNumber());
            }
        }

        System.out.println("Final userId: " + userId);

        // 포인트 적립 (calcul == 0)
        if (calcul == 0) {
            System.out.println("Adding points...");
            pointRepository.addPoints(reqPointDto.getPhoneNumber(), reqPointDto.getPoint());
        }
        // 포인트 차감 (calcul == 1)
        else {
            System.out.println("Subtracting points...");
            pointRepository.subtractPoints(reqPointDto.getPhoneNumber(), reqPointDto.getPoint());
        }
    }
}
