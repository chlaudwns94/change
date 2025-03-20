package com.korit.mcdonaldkiosk.repository.user;

import com.korit.mcdonaldkiosk.entity.User;
import com.korit.mcdonaldkiosk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public User save(User user) {
        userMapper.insertUser(user);
        return user;
    }

    public Optional<User> findUserByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(userMapper.findUserByPhoneNumber(phoneNumber));
    }
}
