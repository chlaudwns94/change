package com.korit.mcdonaldkiosk.controller.admin;

import com.korit.mcdonaldkiosk.service.admin.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class SalesController {


    @Autowired
    private SalesService salesService;

    @GetMapping("/sales")
    public ResponseEntity<?> getSales() {
        return ResponseEntity.ok().body(salesService.getSales());
    }

    @GetMapping("/menusales")
    public ResponseEntity<?> getSalesByMenu() {
        return ResponseEntity.ok(salesService.getSalesByMenu());
    }

}
