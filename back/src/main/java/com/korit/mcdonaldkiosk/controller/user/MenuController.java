package com.korit.mcdonaldkiosk.controller.user;

import com.korit.mcdonaldkiosk.entity.Menu;
import com.korit.mcdonaldkiosk.service.user.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 사용자 메뉴 관련 데이터를 제공하는 컨트롤러
 *
 * - 사용자가 메뉴 리스트를 조회할 수 있는 API를 제공한다.
 */
@RestController()
@RequestMapping("/user")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 메뉴 리스트 조회 API
     *
     * @return 모든 메뉴 리스트 데이터 응답
     */
    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> getMenus() {
        List<Menu> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }
}
