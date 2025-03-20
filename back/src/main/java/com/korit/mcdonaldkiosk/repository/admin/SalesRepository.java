package com.korit.mcdonaldkiosk.repository.admin;

import com.korit.mcdonaldkiosk.entity.MenuSales;
import com.korit.mcdonaldkiosk.entity.Sales;
import com.korit.mcdonaldkiosk.mapper.SalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalesRepository {

    @Autowired
    private SalesMapper salesMapper;

    public List<Sales> getAllSales() {
        return salesMapper.getSales();
    }

    public List<MenuSales> findSales() {
        return salesMapper.findSales();
    }
}
