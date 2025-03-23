package com.korit.mcdonaldkiosk.mapper;

import com.korit.mcdonaldkiosk.entity.Order;
import com.korit.mcdonaldkiosk.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OrderMapper 인터페이스
 *
 * 주문 관련 데이터베이스 작업을 수행하는 MyBatis 매퍼 인터페이스
 * - 주문 목록 추가, 주문 상세 정보 추가 및 총 매출 업데이트 작업을 제공
 */
@Mapper
public interface OrderMapper {

    /**
     * 주문 목록을 데이터베이스에 추가하는 메서드
     *
     * @param order Order 객체 (주문 정보)
     */
    void addOrderList(Order order);

    /**
     * 여러 개의 주문 상세 정보를 데이터베이스에 추가하는 메서드
     *
     * @param orders 주문 상세 정보 리스트
     */
    void addOrders(@Param("orders") List<OrderDetail> orders);

    /**
     * 주어진 주문 ID에 대해 총 매출을 업데이트하는 메서드
     *
     * @param orderId 주문 ID
     * @return 업데이트된 행의 수
     */
    int updateTotalSales(int orderId);
}
