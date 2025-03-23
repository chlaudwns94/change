package com.korit.mcdonaldkiosk.controller.admin;

import com.korit.mcdonaldkiosk.service.admin.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 매출 관련 데이터를 제공하는 컨트롤러
 *
 * - 전체 매출 및 메뉴별 매출 데이터를 조회할 수 있는 API를 제공한다.
 */
@RestController
@RequestMapping("/admin")
public class SalesController {

    @Autowired
    private SalesService salesService;

    /**
     * 전체 매출 조회 API
     *
     * @return 전체 매출 데이터 응답
     */
    @GetMapping("/sales")
    public ResponseEntity<?> getSales() {
        return ResponseEntity.ok().body(salesService.getSales());
    }

    /**
     * 메뉴별 매출 조회 API
     *
     * @return 메뉴별 매출 데이터 응답
     */
    @GetMapping("/menusales")
    public ResponseEntity<?> getSalesByMenu() {
        return ResponseEntity.ok(salesService.getSalesByMenu());
    }
}
