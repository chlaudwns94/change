package com.korit.mcdonaldkiosk.mapper;

import com.korit.mcdonaldkiosk.entity.MenuSales;
import com.korit.mcdonaldkiosk.entity.Sales;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesMapper {
    public List<Sales> getSales();
    public List<MenuSales> findSales();
}
