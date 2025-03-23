package com.korit.mcdonaldkiosk.controller.admin;

import com.korit.mcdonaldkiosk.dto.request.ReqMenuListDto;
import com.korit.mcdonaldkiosk.dto.response.RespMenuListDto;
import com.korit.mcdonaldkiosk.entity.Menu;
import com.korit.mcdonaldkiosk.entity.MenuPrice;
import com.korit.mcdonaldkiosk.service.admin.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminMenuController {

    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 모든 카테고리 목록을 조회하는 API
     *
     * @return 카테고리 목록 (중복 제거 후 반환)
     */
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        List<Menu> allCategories = adminMenuService.getAllCategories();
        // 객체에서 카테고리 값만 추출하여 반환
        List<String> categories = allCategories
                .stream()
                .map(Menu::getMenuCategory)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(categories);
    }

    /**
     * 모든 메뉴 리스트를 조회하는 API
     *
     * @return 모든 메뉴 리스트
     */
    @GetMapping("/menus1")
    public ResponseEntity<List<Menu>> getAllMenuList() {
        return ResponseEntity.ok().body(adminMenuService.getAllAdminMenuList());
    }

    /**
     * 특정 카테고리에 대한 메뉴 리스트 및 페이지 정보 조회 API
     *
     * @param dto 메뉴 검색 요청 DTO (카테고리, 페이지, 제한 개수 포함)
     * @return 페이지네이션된 메뉴 리스트 응답 DTO
     */
    @GetMapping("/list")
    public ResponseEntity<?> searchBoardList(@ModelAttribute ReqMenuListDto dto) {
        // 전체 메뉴 개수 조회
        int totalMenuListCount = adminMenuService.getMenuListCountByCategory(dto.getCategory());
        // 총 페이지 수 계산
        int totalPages = totalMenuListCount * dto.getLimitCount() == 0
                ? totalMenuListCount / dto.getLimitCount()
                : totalMenuListCount / dto.getLimitCount() + 1;

        // 입력받은 카테고리에 해당하는 메뉴가 존재하는지 확인
        String filteredCategory = adminMenuService.getAllCategories()
                .stream()
                .filter(menu -> menu.getMenuCategory().equals(dto.getCategory()))
                .map(Menu::getMenuCategory)
                .collect(Collectors.joining(","));

        if (filteredCategory.isEmpty()) {
            dto.setCategory("전체");
        }

        // 페이지네이션 정보를 포함한 응답 객체 생성
        RespMenuListDto respMenuListDto =
                RespMenuListDto.builder()
                        .page(dto.getPage())
                        .limitCount(dto.getLimitCount())
                        .totalPages(totalPages)
                        .totalElements(totalMenuListCount)
                        .isFirstPage(dto.getPage() == 1)
                        .isLastPage(dto.getPage() == totalPages)
                        .nextPage(dto.getPage() != totalPages ? dto.getPage() + 1 : 0)
                        .menuList(adminMenuService.getMenuListSearchByCategory(dto)) // 검색 조건에 맞는 메뉴 목록 조회
                        .build();

        return ResponseEntity.ok().body(respMenuListDto);
    }

    /**
     * 모든 메뉴 조회 API
     *
     * @return 전체 메뉴 리스트
     */
    @GetMapping("/menus")
    public ResponseEntity<?> getAllMenus() {
        return ResponseEntity.ok().body(adminMenuService.getAllMenus());
    }

    /**
     * 특정 메뉴의 가격 정보 조회 API
     *
     * @param menuId 조회할 메뉴의 ID
     * @return 해당 메뉴의 가격 정보 리스트
     */
    @GetMapping("/menus/{menuId}/prices")
    public ResponseEntity<?> getMenuPrices(@PathVariable int menuId) {
        return ResponseEntity.ok().body(adminMenuService.getMenuPrices(menuId));
    }

    /**
     * 새로운 메뉴 추가 API (가격 정보 포함)
     *
     * @param menu   추가할 메뉴 객체
     * @param prices 해당 메뉴의 가격 리스트
     */
    @PostMapping("/menus")
    public void addMenu(@RequestBody Menu menu, @RequestParam List<MenuPrice> prices) {
        adminMenuService.addMenu(menu, prices);
    }

    /**
     * 메뉴 삭제 API
     *
     * @param menuId 삭제할 메뉴의 ID
     */
    @DeleteMapping("/menus/{menuId}")
    public void deleteMenu(@PathVariable int menuId) {
        adminMenuService.deleteMenu(menuId);
    }

}
