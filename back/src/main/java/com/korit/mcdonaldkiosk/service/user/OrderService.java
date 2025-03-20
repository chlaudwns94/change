package com.korit.mcdonaldkiosk.service.user;

import com.korit.mcdonaldkiosk.dto.request.ReqUserOrderDto;
import com.korit.mcdonaldkiosk.entity.MenuPrice;
import com.korit.mcdonaldkiosk.entity.Order;
import com.korit.mcdonaldkiosk.entity.OrderDetail;  // OrderDetail 추가
import com.korit.mcdonaldkiosk.mapper.OrderMapper;
import com.korit.mcdonaldkiosk.repository.user.MenuRepository;
import com.korit.mcdonaldkiosk.repository.user.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuRepository menuRepository;  // MenuPriceRepository 추가

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public void orderMenu(List<ReqUserOrderDto> reqUserOrderDtos) {
        // 1. 주문을 생성하고 orderId를 받음
        Order order = new Order();
        orderRepository.addOrder(order);

        if (order.getOrderId() == null) {
            throw new RuntimeException("주문 ID 생성 실패");
        }

        // 2. 생성된 orderId를 주문 상세 정보에 추가
        List<OrderDetail> orderDetails = reqUserOrderDtos.stream()
                .map(dto -> {
                    // menuId를 통해 menu_price_tb에서 가격 정보를 가져옴
                    MenuPrice menuPrice = menuRepository.findMenuById(dto.getMenuPriceId());

                    if (menuPrice == null) {
                        throw new RuntimeException("메뉴 가격 정보를 찾을 수 없습니다.");
                    }

                    // isSet 값에 따라 가격을 다르게 설정
                    int price = dto.getIsSet() == 1 ? menuPrice.getDiscountPrice() : menuPrice.getMenuPrice();
                    int discount = dto.getIsSet() == 0 ? menuPrice.getMenuPrice() : menuPrice.getDiscountPrice();

                    // OrderDetail 객체 생성
                    return dto.toEntity(order.getOrderId(), price, discount);
                })
                .collect(Collectors.toList());

        // 3. 주문 상세 정보 저장 (MyBatis 매퍼 호출)
        orderMapper.addOrders(orderDetails);  // MyBatis 매퍼를 통해 주문 상세 정보 추가
        // 4. total_sales 업데이트
        orderMapper.updateTotalSales(order.getOrderId());
    }
}

