package com.korit.mcdonaldkiosk.mapper;

import com.korit.mcdonaldkiosk.entity.Order;
import com.korit.mcdonaldkiosk.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    void addOrderList(Order order);

    void addOrders(@Param("orders") List<OrderDetail> orders);

    int updateTotalSales(int orderId);
}
