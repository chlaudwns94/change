package com.korit.mcdonaldkiosk.repository.user;

import com.korit.mcdonaldkiosk.entity.Menu;
import com.korit.mcdonaldkiosk.entity.MenuPrice;
import com.korit.mcdonaldkiosk.mapper.MenuMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository {

    private final MenuMapper menuMapper;

    public MenuRepository(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    // 모든 메뉴 리스트를 반환
    public List<Menu> findAllMenus() {
        return menuMapper.selectAllMenus();
    }

    public MenuPrice findMenuById(int menuId) {
        return menuMapper.find1ByMenuPriceId(menuId);
    }
}