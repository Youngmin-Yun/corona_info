package com.coronainfo.service;

import com.coronainfo.mapper.RegionalInfoMapper;
import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;
import com.coronainfo.vo.CoronaVaccineInfoVO;

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
}
