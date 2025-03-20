package com.korit.mcdonaldkiosk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    private int orderDetailId;
    private Integer orderId;
    private int menuPriceId;
    private int menuCount;
    private int isSet;
    private int menuPrice;
    private int discountPrice;
}

//세일즈
/*
    메뉴 선택 -> 갯수및 옵션이 디테일에 저장 -> 모든 선택된게 오더에 저장 ->

 */
