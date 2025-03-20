package com.korit.mcdonaldkiosk.service.user;

import com.korit.mcdonaldkiosk.entity.Menu;
import com.korit.mcdonaldkiosk.repository.user.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    // 메뉴 리스트를 반환
    public List<Menu> getAllMenus() {
        return menuRepository.findAllMenus();
    }
}
