package com.coronainfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InternationalInfoController {
    @GetMapping("/international/info")
    public String getInternationaInformation(){
        return "/international/internationalInfo";
    }
}
