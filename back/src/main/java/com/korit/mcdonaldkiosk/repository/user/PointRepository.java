package com.korit.mcdonaldkiosk.repository.user;

import com.korit.mcdonaldkiosk.entity.Point;
import com.korit.mcdonaldkiosk.mapper.PointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointRepository {

    @Autowired
    private PointMapper pointMapper;

    // 포인트 차감 메서드 (calcul=1)
    public void subtractPoints(String phoneNumber, int point) {
        int userId = findUserIdByPhoneNumber(phoneNumber);

        if (userId == 0) {
            throw new IllegalArgumentException("User not found for phone number: " + phoneNumber);
        }

        System.out.println("Subtracting points for userId: " + userId);

        // 기존 포인트 값을 가져오기
        Point currentPoint = pointMapper.findCurrentPointByUserId(userId);
        if (currentPoint == null) {
            throw new IllegalArgumentException("Current points not found for userId: " + userId);
        }

        // 포인트 차감: 현재 포인트에서 전달받은 포인트만큼 차감
        int updatedPoint = currentPoint.getPoint() - point;

        // 차감된 포인트가 0 이상이어야만 업데이트
        if (updatedPoint < 0) {
            throw new IllegalArgumentException("Insufficient points for userId: " + userId);
        }

        // 포인트 업데이트
        Point updatePoint = new Point();
        updatePoint.setUserId(userId);
        updatePoint.setPoint(updatedPoint);
        pointMapper.updatePoint(updatePoint);
        System.out.println("Points subtracted for userId " + userId);
    }

    public void addPoints(String phoneNumber, int point) {
        int userId = findUserIdByPhoneNumber(phoneNumber);

        if (userId == 0) {
            throw new IllegalArgumentException("User not found for phone number: " + phoneNumber);
        }

        // 기존 포인트 값을 가져오기
        Point currentPoint = pointMapper.findCurrentPointByUserId(userId);
        if (currentPoint == null) {
            // 새 유저라면 기본 포인트 0 설정
            currentPoint = new Point();
            currentPoint.setUserId(userId);
            currentPoint.setPoint(0);
        }

        // 포인트 적립: 기존 포인트에 추가
        int updatedPoint = currentPoint.getPoint() + point;

        // 포인트가 변경되었을 때만 업데이트
        if (updatedPoint != currentPoint.getPoint()) {
            // 포인트 업데이트
            Point updatePoint = new Point();
            updatePoint.setUserId(userId);
            updatePoint.setPoint(updatedPoint);

            // updatePoint 호출 후 반영되는지 확인
            int result = pointMapper.updatePoint(updatePoint);
            if (result > 0) {
                System.out.println("Points added for userId " + userId + " new points: " + updatedPoint);
            } else {
                System.out.println("Failed to update points for userId " + userId);
            }
        } else {
            System.out.println("No update needed for userId " + userId + " current points: " + currentPoint.getPoint());
        }
    }
    // 유저 생성 메서드
    public void save(String phoneNumber) {
        int userId = findUserIdByPhoneNumber(phoneNumber);

        if (userId == 0) {
            // 유저가 없으면 새로 생성
            createNewUser(phoneNumber);
            System.out.println("New user created for phoneNumber: " + phoneNumber);
        }
    }

    // 핸드폰 번호로 유저 ID 조회
    public Integer findUserIdByPhoneNumber(String phoneNumber) {
        Integer userId = pointMapper.findUserIdByPhoneNumber(phoneNumber);
        return userId != null ? userId : 0; // null이면 0 반환
    }

    // 새로운 유저 생성 (user_tb에 저장)
    public void createNewUser(String phoneNumber) {
        pointMapper.createNewUser(phoneNumber);
    }
}
