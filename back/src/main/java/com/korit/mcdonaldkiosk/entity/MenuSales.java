package com.korit.mcdonaldkiosk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuSales {
    private int menuSalesId;
    private int menuId;
    private int orderYear;
    private int orderMonth;
    private int totalCount;
    private int sales;
}
