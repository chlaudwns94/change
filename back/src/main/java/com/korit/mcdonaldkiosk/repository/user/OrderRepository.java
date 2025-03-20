package com.korit.mcdonaldkiosk.repository.user;

import com.korit.mcdonaldkiosk.entity.Order;
import com.korit.mcdonaldkiosk.entity.OrderDetail;
import com.korit.mcdonaldkiosk.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    // 주문 리스트 추가
    public void addOrder(Order order){
        orderMapper.addOrderList(order);
    };

    // 주문 상세 정보 추가
    public  void addOrderDetails(List<OrderDetail> orderDetails){
        orderMapper.addOrders(orderDetails);
    };
}
