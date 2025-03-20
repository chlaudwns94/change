package com.korit.mcdonaldkiosk.service.admin;

import com.korit.mcdonaldkiosk.dto.response.SalesRespDto;
import com.korit.mcdonaldkiosk.entity.MenuSales;
import com.korit.mcdonaldkiosk.entity.Sales;
import com.korit.mcdonaldkiosk.repository.admin.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public List<SalesRespDto> getSales() {
        List<Sales> sales = salesRepository.getAllSales();

        return sales.stream().map(Sales::toAdminSalesRespDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public List<MenuSales> getSalesByMenu() {
        salesRepository.findSales();

        return salesRepository.findSales();
    }

}