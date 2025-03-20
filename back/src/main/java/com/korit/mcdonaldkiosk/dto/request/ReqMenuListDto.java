package com.korit.mcdonaldkiosk.dto.request;

import lombok.Data;

@Data
public class ReqMenuListDto {
    private int page;
    private int limitCount;
    private String category;
}
