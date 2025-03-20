package com.korit.mcdonaldkiosk.mapper;

import com.korit.mcdonaldkiosk.entity.Point;
import com.korit.mcdonaldkiosk.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointMapper {

    // 포인트 적립: Point 객체를 받아서 insert
    int insertPoint(Point point);

    // 포인트 차감: Point 객체를 받아서 update
    int updatePoint(Point point);

    // 핸드폰 번호로 포인트 조회
    Integer findUserIdByPhoneNumber(String phoneNumber);

    // 새 유저 생성 (user_tb에 저장)
    int createNewUser(String phoneNumber);

    Point findCurrentPointByUserId(int userId);
}
