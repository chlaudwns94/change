package com.korit.mcdonaldkiosk.service.user;

import com.korit.mcdonaldkiosk.dto.request.ReqAdminSignUpDto;
import com.korit.mcdonaldkiosk.dto.request.ReqUserDto;
import com.korit.mcdonaldkiosk.dto.request.ReqUserFindDto;
import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.entity.User;
import com.korit.mcdonaldkiosk.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public User save(ReqUserDto reqUserDto) {
        User user = User.builder()
                .phoneNumber(reqUserDto.getPhoneNumber())
                .build();
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public User findUser(ReqUserFindDto reqUserFindDto) {
        User user = userRepository.findUserByPhoneNumber(reqUserFindDto.getPhoneNumber())
                .orElseThrow(() -> new UsernameNotFoundException("사용자정보를 다시확인하세요"));
        return user;
    }
}
