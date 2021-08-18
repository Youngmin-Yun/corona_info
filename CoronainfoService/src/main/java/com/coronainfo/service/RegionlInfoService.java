package com.coronainfo.service;

import java.util.List;

import com.coronainfo.mapper.RegionalInfoMapper;
import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;
import com.coronainfo.vo.CoronaVaccineInfoVO;
import com.coronainfo.vo.CoronaWeeksVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionlInfoService {
    @Autowired
    RegionalInfoMapper mapper;
    public CoronaSidoVO selectRegionalCoronaInfo(String region, String date){
        return mapper.selectRegionalCoronaInfo(region, date);
    }
    public CoronaInfoVO selectCoronaInfoRegionTotal(String date){
        return mapper.selectCoronaInfoRegionTotal(date);
    }
    public CoronaVaccineInfoVO selectCoronaVaccineStatus(String region, String date){
        return mapper.selectCoronaVaccineStatus(region, date);
    }
    public String selectDangerAge(String date){
        return mapper.selectDangerAge(date);
    }
    public List<CoronaWeeksVO> selectRegionalCoronaTwoWeeks (String region, String date){
        return mapper.selectRegionalCoronaTwoWeeks(region, date);
    }
    public List<CoronaWeeksVO> selectRegionalVaccineTwoWeeks (String region, String date){
        return mapper.selectRegionalVaccineTwoWeeks(region, date);
    }
    public List<CoronaWeeksVO> selectVaccineInfo(String date){
        return mapper.selectVaccineInfo(date);
    }
}
