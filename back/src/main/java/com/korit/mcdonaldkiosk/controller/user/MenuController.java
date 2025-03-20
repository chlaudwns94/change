package com.korit.mcdonaldkiosk.controller.user;

import com.korit.mcdonaldkiosk.entity.Menu;
import com.korit.mcdonaldkiosk.service.user.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // 메뉴 리스트 반환
    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> getMenus() {
        List<Menu> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }
}