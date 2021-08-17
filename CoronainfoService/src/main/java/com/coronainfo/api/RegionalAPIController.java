package com.coronainfo.api;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.coronainfo.service.RegionlInfoService;
import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;
import com.coronainfo.vo.CoronaVaccineInfoVO;

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
            // Date now = new Date();
            // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            // date = formatter.format(now);
            Calendar now = Calendar.getInstance();
            Calendar standard = Calendar.getInstance();
            standard.set(Calendar.HOUR_OF_DAY, 11);
            standard.set(Calendar.MINUTE, 00);
            standard.set(Calendar.SECOND, 00);
    
            if(now.getTimeInMillis() < standard.getTimeInMillis()){
                // 현재 접속시간이 기준시간보다 이전일때
                // 하루 전 날의 날짜로 변경하고 정보를 가져온다.
                now.add(Calendar.DATE, -1);
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.format(now.getTime());
        }
        CoronaSidoVO vo = service.selectRegionalCoronaInfo(region, date);
        resutlMap.put("data", vo);
        return resutlMap;
    }
    @GetMapping("/api/corona/vaccineStatus")
    public Map<String, Object> getRegionCoronaVaccineStatus(@RequestParam String region, @RequestParam @Nullable String date){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(date == null){
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.format(now);
        }
        CoronaVaccineInfoVO vo = service.selectCoronaVaccineStatus(region, date);
        Integer accFirstCnt = vo.getAccFirstCnt(); 
        Integer accSecondCnt = vo.getAccSecondCnt();
        Integer firstCnt = vo.getFirstCnt();
        Integer secondCnt = vo.getSecondCnt();

        Integer firstAll = accFirstCnt + firstCnt;
        Integer secondAll = accSecondCnt + secondCnt;

        DecimalFormat dFormat = new DecimalFormat("###,###");
        String firstshot = dFormat.format(firstAll);
        String secondshot = dFormat.format(secondAll);

        vo.setFirstshot(firstshot);
        vo.setSecondshot(secondshot);

        resultMap.put("data", vo);
        return resultMap;
    }
}
