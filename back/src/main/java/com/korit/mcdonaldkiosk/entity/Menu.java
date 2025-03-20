package com.korit.mcdonaldkiosk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {
    private int menuId;
    private String menuName;
    private String menuCategory;
    private int menuSequence;
    private String singleImg;
    private String setImg;
    private int isExposure;

    private List<MenuPrice> menuPrice;
}