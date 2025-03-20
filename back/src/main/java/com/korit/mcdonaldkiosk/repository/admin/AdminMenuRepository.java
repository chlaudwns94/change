package com.korit.mcdonaldkiosk.repository.admin;


import com.korit.mcdonaldkiosk.entity.Menu;
import com.korit.mcdonaldkiosk.entity.MenuPrice;
import com.korit.mcdonaldkiosk.entity.Order;
import com.korit.mcdonaldkiosk.mapper.AdminMenuMapper;
import com.korit.mcdonaldkiosk.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminMenuRepository {
    @Autowired
    private AdminMenuMapper adminMenuMapper;

    // 모든 카테고리를 반환
    public List<Menu> findAllCategories() {
        return adminMenuMapper.selectAllCategories();
    }

    // 모든 메뉴 리스트를 반환
    public List<Menu> findAllAdminMenus() {
        return adminMenuMapper.selectAllAdminMenus();
    }

    // 카테고리에 해당하는 메뉴 갯수를 반환(페이지관리)
    public int findMenuCountAllBySearchCategory(String searchCategory) {
        return adminMenuMapper.selectMenuCountAllByCategory(searchCategory);
    }

    // 카테고리에 해당하는 메뉴를 반환
    public List<Menu> findMenuListByCategory(
            int startIndex,
            int limitSize,
            String category) {
        return adminMenuMapper.selectMenuListByCategory(startIndex, limitSize, category);

    }
    // 전체 메뉴 조회
    public Optional<List<Menu>> getAllMenus() {
        List<Menu> foundMenus = adminMenuMapper.selectAllMenus();
        return foundMenus.isEmpty() ? Optional.empty() : Optional.of(foundMenus);
    }

    // 특정 메뉴 가격 조회
    public Optional<List<MenuPrice>> getMenuPrices(int menuId) {
        List<MenuPrice> foundMenuPrice = adminMenuMapper.getMenuPrices(menuId);
        return foundMenuPrice.isEmpty() ? Optional.empty() : Optional.of(foundMenuPrice);
    }

    public Optional<Boolean> addMenu(Menu menu, List<MenuPrice> menuPrices) {
        try {
            // 1. 메뉴 데이터 추가 (menu_tb)
            adminMenuMapper.insertMenu(menu);

            // 2. 가격 데이터 추가 (menu_price_tb)
            if (!menuPrices.isEmpty()) {
                adminMenuMapper.insertMenuPrices(menu.getMenuId(), menuPrices);
            }

            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(false);
        }
    }

    public Optional<Boolean> deleteMenu(int menuId) {
        try {
            // 1. 가격 정보 삭제 (menu_price_tb) - menu_id 외래키 참조로 인해 먼저 삭제 필요
            adminMenuMapper.deleteMenuPrices(menuId);

            // 2. 메뉴 정보 삭제 (menu_tb)
            int deletedRows = adminMenuMapper.deleteMenu(menuId);

            // 삭제된 행이 없으면 false 반환
            if (deletedRows == 0) {
                return Optional.of(false);
            }

            return Optional.of(true); // ✅ 성공 시 true 반환
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(false); // ❌ 실패 시 false 반환
        }
    }

}
