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

@RestController
@RequestMapping("/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<?> orderMenus(@RequestBody List<ReqUserOrderDto> reqUserOrderDtos) {
        orderService.orderMenu(reqUserOrderDtos);

        return ResponseEntity.ok(true);
    }
}
