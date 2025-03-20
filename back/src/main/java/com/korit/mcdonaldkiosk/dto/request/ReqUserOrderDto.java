package com.korit.mcdonaldkiosk.dto.request;

import com.korit.mcdonaldkiosk.entity.Order;
import com.korit.mcdonaldkiosk.entity.OrderDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqUserOrderDto {
    private int menuPriceId;
    private int menuCount;
    private int isSet;

    public OrderDetail toEntity(int orderId, int price, int discount) {
        return OrderDetail.builder()
                .orderId(orderId)
                .menuPriceId(menuPriceId)
                .menuCount(menuCount)
                .isSet(isSet)
                .menuPrice(price)
                .discountPrice(discount)
                .build();
    }
}
