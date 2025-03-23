package com.korit.mcdonaldkiosk.controller.user;

import com.korit.mcdonaldkiosk.dto.request.ReqUserOrderDto;
import com.korit.mcdonaldkiosk.service.user.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 사용자 주문 관련 데이터를 처리하는 컨트롤러
 *
 * - 사용자가 주문을 생성하는 API를 제공한다.
 */
@RestController
@RequestMapping("/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 메뉴 주문 API
     *
     * @param reqUserOrderDtos 주문할 메뉴와 관련된 데이터를 담은 요청 객체
     * @return 주문 처리 결과 응답
     */
    @PostMapping("/order")
    public ResponseEntity<?> orderMenus(@RequestBody List<ReqUserOrderDto> reqUserOrderDtos) {
        orderService.orderMenu(reqUserOrderDtos);

        return ResponseEntity.ok(true);
    }
}
