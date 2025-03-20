package com.korit.mcdonaldkiosk.mapper;

import com.korit.mcdonaldkiosk.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    User findUserByPhoneNumber(String phoneNumber);
}
