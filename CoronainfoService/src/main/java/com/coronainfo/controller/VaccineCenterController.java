package com.coronainfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VaccineCenterController {
    @GetMapping("/veccineCenter")
    public String getVeccineCenter(){
        return "/center/veccineCenter";
    }
    @GetMapping("/vaccineCenter/consigned")
    public String getConsignedCenter(){
        return "/center/consignedCenter";
    }
}
