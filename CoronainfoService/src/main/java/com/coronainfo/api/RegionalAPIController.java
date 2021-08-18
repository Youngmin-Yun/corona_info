package com.coronainfo.api;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.coronainfo.service.RegionlInfoService;
import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;
import com.coronainfo.vo.CoronaVaccineInfoVO;
import com.coronainfo.vo.CoronaWeeksVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegionalAPIController {
    @Autowired
    RegionlInfoService service;
    @GetMapping("/api/regional")
    public Map<String, Object> getRegionalInfo(@RequestParam String region, @RequestParam @Nullable String date){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(date == null || date.equals("")){
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
        if(vo == null){
            resultMap.put("status", false);
            resultMap.put("data", null);
            resultMap.put("message", "데이터가 없습니다. ("+region+","+date+")");
            return resultMap;
        }
        Integer defCnt = vo.getDefCnt();
        Integer incDec = vo.getIncDec();
        Integer isolClearCnt = vo.getIsolClearCnt();
        Integer isolIngCnt = vo.getIsolIngCnt();
        
        DecimalFormat dFormatter = new DecimalFormat("###,###,###");
        String strDefCnt = dFormatter.format(defCnt);
        String strIncDec = dFormatter.format(incDec);
        String strIsolClearCnt = dFormatter.format(isolClearCnt);
        String strIsolingCnt = dFormatter.format(isolIngCnt);

        vo.setStrDefCnt(strDefCnt);
        vo.setStrIncDec(strIncDec);
        vo.setStrIsolClearCnt(strIsolClearCnt);
        vo.setStrIsolingCnt(strIsolingCnt);
        
        resultMap.put("data", vo);

        String dangerAge = service.selectDangerAge(date);
        resultMap.put("dangerAge", dangerAge);

        List<CoronaWeeksVO> coronaWeeksList = service.selectRegionalCoronaTwoWeeks(region, date);
        resultMap.put("coronaWeeksList", coronaWeeksList);

        return resultMap;
    }
    @GetMapping("/api/corona/vaccineStatus")
    public Map<String, Object> getRegionCoronaVaccineStatus(@RequestParam String region, @RequestParam @Nullable String date){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(date == null||date.equals("")){
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
        CoronaVaccineInfoVO vo = service.selectCoronaVaccineStatus(region, date);
        if(vo == null){
            resultMap.put("status", false);
            resultMap.put("data", null);
            resultMap.put("message", "데이터가 없습니다. ("+region+","+date+")");
            return resultMap;
        }
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

        List<CoronaWeeksVO> vaccineList = service.selectRegionalVaccineTwoWeeks(region, date);
        resultMap.put("vaccineList", vaccineList);
        return resultMap;
    }
    
    @GetMapping("/api/vaccine/{date}")
    public Map<String, Object> getVaccineInfoByDate(@PathVariable String date){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<CoronaWeeksVO> list = service.selectVaccineInfo(date);
        resultMap.put("vaccineList", list);
        return resultMap;
    }
}
