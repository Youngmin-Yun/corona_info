package com.coronainfo.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.coronainfo.service.RegionlInfoService;
import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegionalAPIController {
    @Autowired
    RegionlInfoService service;
    @GetMapping("/api/regional")
    public Map<String, Object> getRegionalInfo(@RequestParam String region, @RequestParam @Nullable String date){
        Map<String, Object> resutlMap = new LinkedHashMap<String, Object>();
        if(date == null){
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
            date = formatter.format(now);
        }
        CoronaSidoVO vo = service.selectRegionalCoronaInfo(region, date);
        resutlMap.put("data", vo);
        return resutlMap;
    }
}
