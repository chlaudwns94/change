package com.korit.mcdonaldkiosk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuPrice {
    private int menuPriceId;
    private int menuId;
    private String size;
    private int menuPrice;
    private int discountPrice;

}
