package com.korit.mcdonaldkiosk.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesRespDto {
        private int year;
        private int month;
        private int orderCount;
        private int totalSales;
    }
