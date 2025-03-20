package com.korit.mcdonaldkiosk.entity;

import com.korit.mcdonaldkiosk.dto.response.SalesRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sales {
    private int year;
    private int month;
    private int orderId;
    private int orderCount;
    private int totalSales;

    public SalesRespDto toAdminSalesRespDto() {
        return SalesRespDto.builder()
                .year(year)
                .month(month)
                .orderCount(orderCount)
                .totalSales(totalSales)
                .build();
    }
}
